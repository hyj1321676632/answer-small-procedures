package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.study.system.service.UserInfoSv;
import jxl.format.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private UserInfoSv userInfoSv;

    @RequestMapping(value = "/register")
    public String login() {
        return "register";
    }

    @RequestMapping(value = "/register/saveInfo")
    public ModelAndView save(@ModelAttribute("userInfo") @Validated UserInfo userInfo, BindingResult rs) {
        ModelAndView mode = new ModelAndView();
        if (userInfoSv.checkByUserId(userInfo.getUserId())) {
            mode.addObject("registerError", "账号已存在，请重新注册！");
            mode.setViewName("register");
        } else {
            userInfoSv.saveUserInfo(userInfo);
            mode.setViewName("/");
        }
        return mode;
    }
}
