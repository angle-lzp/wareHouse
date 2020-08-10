package com.angle.web.controller;

import com.angle.domain.Courier;
import com.angle.domain.WareHouse;
import com.angle.service.ICourierService;
import com.angle.service.IWareHouseService;
import com.angle.utils.MapPathUtils;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWareHouseService wareHouseService;
    @Autowired
    private ICourierService courierService;
    @Autowired
    private ServletContext servletContext;

    //分页
    @RequestMapping("/findAllPage")
    @ResponseBody
    public PageModel findAllPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum) {
        PageResult pageResult = wareHouseService.findAllPage(pageNum, 10);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            courier.setAppPassword("0");
            row.setCourier(courier);
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        return pageModel;
    }

    //分页:查询当前登入用户的配送任务
    @RequestMapping("/findCurrentTasks")
    @ResponseBody
    public PageModel findCurrentTasks(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "username") String username, @RequestParam(name = "line", defaultValue = "null") String line, String lonLat) {
        PageResult pageResult = null;
        PageModel pageModel = null;
        if (line.equals("null")) {
            try {
                username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Courier currentCourier = courierService.findByUsernameTag(username, 2);
            //获取该登入用户的id，用于查询配送得客户
            int cid = currentCourier.getId();
            //已经完成了送货的客户
            List<WareHouse> listFinished = wareHouseService.findByImgUrlOver(cid);
            //已经完成了送货的客户
            List<WareHouse> listNOtFinished = wareHouseService.findByImgUrlNoOver(cid);
            //获取该配送员配送的个数
            int total = listFinished.size() + listNOtFinished.size();
            //调用求最短路径工具类
            List<WareHouse> shortestPath = MapPathUtils.getShortestPath(lonLat, listNOtFinished);
            shortestPath.addAll(listFinished);
            pageResult = new PageResult((long) total, shortestPath);
            pageModel = new PageModel(1, total, total);

        } else {
            Courier currentCourier = courierService.findByUsername(username);
            pageResult = wareHouseService.findAllPageByCidAndLine(pageNum, 10, currentCourier.getId(), line);
            pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        }
        pageModel.setList(pageResult.getRows());
        return pageModel;
    }

    //按线路搜索
    @RequestMapping("/search")
    @ResponseBody
    public PageModel search(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "line", defaultValue = "null") String line) {

        //pageNum:当前页，10：一页的数量；line：按line查找
        PageResult pageResult = wareHouseService.findAllPageByLine(pageNum, 10, line);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            if (courier != null) {
                courier.setAppPassword("0");
                row.setCourier(courier);
            }
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        return pageModel;
    }

    //模糊查询
    @RequestMapping("/searchByCondition")
    @ResponseBody
    public PageModel searchByCondition(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "term", defaultValue = "null") String term) {
        try {
            byte[] byteCondition = term.getBytes("ISO-8859-1");
            term = new String(byteCondition, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageResult pageResult = wareHouseService.findAllPageByCondition(pageNum, 10, term);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            if (courier != null) {
                courier.setAppPassword("0");
                row.setCourier(courier);
            }
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        return pageModel;
    }

    //配送任务
    @ResponseBody
    @RequestMapping("/queryTask")
    public PageModel queryTask(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "cid") int cid) {
        //pageNum:当前页，  10：一页的数量；   cid：按cid查找
        PageResult pageResult = wareHouseService.findAllPageByCid(pageNum, 10, cid);
        for (WareHouse row : (List<WareHouse>) pageResult.getRows()) {
            Courier courier = courierService.findById(row.getCid());
            courier.setAppPassword("0");
            row.setCourier(courier);
        }
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        return pageModel;
    }

    //登入
    @RequestMapping("/login")
    @ResponseBody
    public int login(Courier courier, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String username = courier.getUsername();
        String passwrod = courier.getPassword();
        try {
            byte[] byteUsername = username.getBytes("ISO-8859-1");
            byte[] bytePassword = passwrod.getBytes("ISO-8859-1");
            courier.setUsername(new String(byteUsername, "UTF-8"));
            courier.setPassword(new String(bytePassword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Courier courier1 = courierService.findByUsernameTag(courier.getUsername(), 2);
        if (courier1 != null) {
            if (courier.getPassword().equals(courier1.getAppPassword())) {
                session.setAttribute("appCourier", courier1);
                return 1;
            }
        }
        return 0;
    }

    //上传文件
    @RequestMapping("/upload")
    @ResponseBody
    public int upload(MultipartFile pic, @RequestParam(name = "id") int id) {
        //通过id获取该客户的信息：如果一开始上传了图片那么把之前的图片删除图片删除
        WareHouse wareHouseBefore = wareHouseService.findById(id);
        String imgUrl = wareHouseBefore.getImgUrl();
        if (imgUrl != null && !"".equals(imgUrl.trim())) {
            String beforeImgUrl = servletContext.getRealPath(imgUrl);
            File file = new File(beforeImgUrl);
            file.delete();
        }

        String fileName = pic.getOriginalFilename();
        System.out.println(fileName);
        String saveDir = servletContext.getRealPath("/upload");
        System.out.println(saveDir);
        //通过uuid随机数获取唯一的名称
        String uuid = UUID.randomUUID().toString();
        //获取文件后缀，拼接
        fileName = uuid + fileName.substring(fileName.indexOf("."));
        //修改数据库总imgUrl的信息
        wareHouseService.modifyForImgUrlById(id, "/upload" + "/" + fileName);
        try {
            Files.copy(pic.getInputStream(), Paths.get(saveDir, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}