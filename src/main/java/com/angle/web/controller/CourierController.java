package com.angle.web.controller;


import com.angle.domain.Courier;
import com.angle.service.ICourierService;
import com.angle.service.IWareHouseService;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/courier")
public class CourierController {

    @Autowired
    private ICourierService service;
    @Autowired
    private IWareHouseService wareHouseService;


    @ResponseBody
    @RequestMapping("/findAllPage")
    public ModelAndView findAllPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
        //在session中保存当前的页面数，方便后期修改成功后跳回该页面
        HttpSession session = request.getSession();
        session.setAttribute("pageNum", pageNum);
        PageResult pageResult = service.findAllPage(pageNum, 10);
        PageModel pageModel = new PageModel(pageNum, (int) pageResult.getTotal().longValue(), 10);
        pageModel.setList(pageResult.getRows());
        ModelAndView md = new ModelAndView();
        md.addObject("pageModel", pageModel);
        md.setViewName("courier-list");
        return md;
    }


    //修改页面的带数据跳转
    @RequestMapping("/jumpById")
    public String jumpById(int id, Model model) {

        Courier courier = service.findById(id);
        model.addAttribute("courier", courier);
        return "courierOfModify";
    }

    //删除数据
    @RequestMapping("/deleteById")
    public String deleteById(int id, HttpServletRequest request) {

        service.deleteById(id);
        //删除这个配送员的时候也要清除他的任务
        wareHouseService.updateTaskByCid(id);
        HttpSession session = request.getSession();
        return "redirect:/courier/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //通过id修改数据
    @RequestMapping("/modifyById")
    public String modifyById(Courier courier, HttpServletRequest request) {

        System.out.println(courier);
        //获取存储在session中的登入的用户信息
        HttpSession session = request.getSession();
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        courier.setPassword(passwordEncoder.encode(courier.getAppPassword()));
        service.modifyById(courier);
        //更新session中Courier的数据
        if (courier.getTag() == 1) {
            session.setAttribute("courierSecurity", service.findById(courier.getId()));
        }
        return "redirect:/courier/findAllPage?pageNum=" + session.getAttribute("pageNum");
    }

    //插入数据
    @RequestMapping("/insert")
    public String insert(Courier courier) {
        courier.setAppPassword(courier.getPassword());
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        courier.setPassword(passwordEncoder.encode(courier.getPassword()));
        service.insert(courier);
        return "redirect:/courier/findAllPage";
    }

    @RequestMapping("/dddd")
    @ResponseBody
    public String demo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("a", 13);
        session.setAttribute("a", 99);
        return session.getAttribute("a") + "";
    }
}