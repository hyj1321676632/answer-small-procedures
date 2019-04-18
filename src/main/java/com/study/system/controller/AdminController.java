package com.study.system.controller;

import com.study.system.entity.TestTitle;
import com.study.system.entity.UserInfo;
import com.study.system.service.TestTitleSv;
import com.study.system.service.UserInfoSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TestTitleSv testTitleSv;
    @Autowired
    private UserInfoSv userInfoSv;

    @RequestMapping(value = "/admin")
    public ModelAndView admin(HttpSession httpSession){
        ModelAndView mode = new ModelAndView();
        String userId = httpSession.getAttribute("userId").toString();
        mode.addObject("userId",userId);
        mode.setViewName("/admin");
        return mode;
    }

    @RequestMapping(value = "/admin/manager")
    public ModelAndView manager(){
        ModelAndView mode = new ModelAndView();
        List<UserInfo> output = userInfoSv.findAll();
        mode.addObject("userInfoList",output);
        mode.setViewName("/manager");
        return mode;
    }

    @RequestMapping(value = "/admin/saveTitle")
    public ModelAndView save(@ModelAttribute("testTitle") @Validated TestTitle testTitle, BindingResult rs) {
        ModelAndView mode = new ModelAndView();
        testTitleSv.save(testTitle);
        mode.addObject("saveResult","保存成功！" );
        mode.setViewName("/admin");
        return mode;
    }

//    @RequestMapping(value = "/admin/findUserInfo")
//    public ModelAndView findUserInfo(){
//        ModelAndView mode = new ModelAndView();
//        List<UserInfo> output = userInfoSv.findAll();
//        mode.addObject("userInfoList",output);
//        mode.setViewName("/admin");
//        return mode;
//    }

    @RequestMapping("/delete")
    public ModelAndView delete(Long id) {
        ModelAndView mode = new ModelAndView();
        userInfoSv.delete(id);
        List<UserInfo> output = userInfoSv.findAll();
        mode.addObject("userInfoList",output);
        mode.setViewName("/manager");
        return mode;
    }
}
