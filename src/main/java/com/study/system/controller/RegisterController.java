package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.study.system.service.UserInfoSv;
import jxl.format.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String save(@ModelAttribute("userInfo") @Validated UserInfo userInfo, Model model) {
        if (userInfoSv.checkByUserId(userInfo.getUserId())) {
            model.addAttribute("registerError","账号已存在，请重新注册！");
            return "register";
        } else {
            userInfoSv.saveUserInfo(userInfo);
            return "login";
        }
    }
}
