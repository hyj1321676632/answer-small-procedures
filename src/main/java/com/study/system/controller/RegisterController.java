package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.study.system.service.UserInfoSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserInfoSv userInfoSv;

    @RequestMapping(value = "/register")
    public String login() {
        return "register";
    }

    @RequestMapping(value = "/register/saveInfo")
    public String save(@ModelAttribute("userInfo")UserInfo userInfo, Model model) {
        if(StringUtils.isEmpty(userInfo.getUserId()) ){
            model.addAttribute("registerError","请填写注册账号！");
        }else if(StringUtils.isEmpty(userInfo.getUserPassword())){
            model.addAttribute("registerError","请填写账号密码！");
        }else if(StringUtils.isEmpty(userInfo.getUserName())){
            model.addAttribute("registerError","请填写注册人姓名！");
        }else if(StringUtils.isEmpty(userInfo.getUserSchool())){
            model.addAttribute("registerError","请填写注册人学校！");
        }else if(StringUtils.isEmpty(userInfo.getUserSex())){
            model.addAttribute("registerError","请填写注册人性别！");
        }else if (userInfoSv.checkByUserId(userInfo.getUserId())) {
            model.addAttribute("registerError","账号已存在，请重新注册！");
        } else {
            userInfoSv.saveUserInfo(userInfo);
            return "login";
        }
        return "register";
    }
}
