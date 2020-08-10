package com.angle.web.controller;

import com.angle.domain.Courier;
import com.angle.domain.WareHouse;
import com.angle.service.ICourierService;
import com.angle.service.IWareHouseService;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IWareHouseService service;
    @Autowired
    private ICourierService courierService;
    @Autowired
    private ServletContext servletContext;

    @ResponseBody
    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView md = new ModelAndView();
        List<WareHouse> WarehouseList = service.findAll();
        md.addObject("WarehouseList", WarehouseList);
        md.setViewName("client-list");
        return md;
    }

    @ResponseBody
    @RequestMapping("/findAllPage")
    public ModelAndView findAllPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
        //在session中保存当前的页面数，方便后期修改成功后跳回该页面
        HttpSession session = request.getSession();
        session.setAttribute("pageNum", pageNum);

        Courier courierSecurity = (Courier) session.getAttribute("courierSecurity");
        if (courierSecurity == null) {
            //通过security获取用户的名字:存入session中
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Courier courier = courierService.findByUsername(name);
            session.setAttribute("courierSecurity", courier);
        }

        //获取所有配送员数据
        List<Courier> courierList = courierService.findAll();
        //分页获取客户数据
        PageResult pageResult = service.findAllPage(pageNum, 10);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            row.setCourier(courier);
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());

        ModelAndView md = new ModelAndView();
        md.addObject("pageModel", pageModel);
        md.addObject("courierList", courierList);
        md.setViewName("client-list");
        return md;
    }

    //配送任务
    @ResponseBody
    @RequestMapping("/queryTask")
    public ModelAndView queryTask(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "cid") int cid) {
        //pageNum:当前页，10：一页的数量；cid：按cid查找
        PageResult pageResult = service.findAllPageByCid(pageNum, 10, cid);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            row.setCourier(courier);
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        ModelAndView md = new ModelAndView();
        md.addObject("pageModelTask", pageModel);
        md.addObject("taskPageNum", pageNum);
        //查询当前被查看用户得信息
        md.addObject("currentCourier", courierService.findById(cid));
        md.setViewName("clientTask-list");
        return md;
    }

    @ResponseBody
    @RequestMapping("/findAllPagePlus")
    public PageModel findAllPagePlus() {
        return service.findAllPagePlus(1, 10);
    }

    //修改页面的带数据跳转
    @RequestMapping("/jumpById")
    public String jumpById(int id, Model model) {

        WareHouse wareHouse = service.findById(id);
        model.addAttribute("wareHouse", wareHouse);
        return "client-modify";
    }

    //通过id修改数据
    @RequestMapping("/modifyById")
    public String modifyById(WareHouse wareHouse, HttpServletRequest request) {
        service.modifyById(wareHouse);
        HttpSession session = request.getSession();
        return "redirect:/index/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //删除数据
    @RequestMapping("/deleteById")
    public String deleteById(int id, HttpServletRequest request) {
        service.deleteById(id);
        HttpSession session = request.getSession();
        return "redirect:/index/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //插入数据
    @RequestMapping("/insert")
    public String insert(WareHouse wareHouse, HttpServletRequest request) {
        service.insert(wareHouse);
        return "redirect:/index/findAllPage";
    }

    //选择数据数据
    @RequestMapping("/checkData")
    public String checkData(String ids, Integer cid, HttpServletRequest request) {
        String[] idss = ids.split(",");
        for (String id : idss) {
            //把每个客户对应的送货人对应起来
            service.modifyOnlyCid(Integer.parseInt(id), cid);//id:客户的id，cid：配送员的id
        }
        HttpSession session = request.getSession();
        return "redirect:/index/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //模糊查询
    @RequestMapping("/search")
    public String search(Model model, @RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "line", defaultValue = "null") String line, @RequestParam(name = "other", defaultValue = "null") String other, HttpServletRequest request) {

        HttpSession session = request.getSession();
        //System.out.println((line + "  " + other));
        try {
            byte[] byteLine = line.getBytes("ISO-8859-1");
            byte[] byteOther = other.getBytes("ISO-8859-1");

            line = new String(byteLine, "UTF-8");
            other = new String(byteOther, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //System.out.println((line + "  " + other));
        PageResult pageResult = null;
        //line:不为空；other:为空:按线路查找
        if (!line.equals("null") && other.equals("null")) {
            //searchLine += line;
            //pageNum:当前页，10：一页的数量；line：按line查找
            pageResult = service.findAllPageByLine(pageNum, 10, line);
            for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
                Courier courier = courierService.findById(row.getCid());
                row.setCourier(courier);
            }
            //对客户名字，地址进行模糊查找
        } else if (line.equals("null") && !other.equals("null")) {
            //searchLine += other;
            pageResult = service.findAllPageByOther(pageNum, 10, other);
            for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
                Courier courier = courierService.findById(row.getCid());
                row.setCourier(courier);
            }
            //对线路精准查找 and 客户名字，地址的模糊查找
        } else if (!line.equals("null") && !other.equals("null")) {
            //searchLine += line + " " + other;
            //pageNum:当前页，10：一页的数量；line：按line查找
            pageResult = service.findAllPageByAll(pageNum, 10, line, other);
            for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
                Courier courier = courierService.findById(row.getCid());
                row.setCourier(courier);
            }
        }
        //System.out.println(pageResult.getRows());
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        model.addAttribute("pageModelSearch", pageModel);
        model.addAttribute("searchLine", line);
        model.addAttribute("searchOther", other);
        return "clientSearch-list";
    }

    //仅仅是清空上传的照片和配送员
    @RequestMapping("/restart")
    public String restart(HttpServletRequest request) {
        List<WareHouse> list = service.findAll();
        for (WareHouse warehouse : list) {
            String imgUrl = warehouse.getImgUrl();
            if (imgUrl != null && !"".equals(imgUrl.trim())) {
                String beforeImgUrl = servletContext.getRealPath(imgUrl);
                System.out.println(beforeImgUrl);
                File file = new File(beforeImgUrl);
                file.delete();
            }
            service.modifyImgUrlById(warehouse);
        }
        HttpSession session = request.getSession();
        return "redirect:/index/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //移除该配送员的任务
    @RequestMapping("/removeTask")
    public String removeTask(Integer id, Integer taskPageNum, int cid) {
        service.modifyByIdForCid(id);
        return "redirect:/index/queryTask?pageNum=" + taskPageNum + "&cid=" + cid;
    }
}