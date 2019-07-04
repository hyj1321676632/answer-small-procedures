package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.study.system.service.UserInfoSv;
import com.study.system.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserInfoSv userInfoSv;

    @RequestMapping(value = "/")
    public String login(){
        //销毁session信息，返回登录页
        SessionUtils.destroySesson();
        return "login";
    }

    @RequestMapping(value = "/check")
    public ModelAndView save(@ModelAttribute("userInfo") @Validated UserInfo userInfo, HttpServletRequest request) {
        ModelAndView mode = new ModelAndView();
        UserInfo checkResult = userInfoSv.checkData(userInfo);
        if(checkResult != null){
            String userId = userInfo.getUserId();
            String password = userInfo.getUserPassword();
            //将用户保存到session中
            SessionUtils.setSession(userId,password,request);
            mode.addObject("userId",userId);
            String role = checkResult.getUserRole();
            if(role.equals("admin")){
                mode.setViewName("/admin");
            }else {
                mode.setViewName("/index");
            }
        }else{
            mode.addObject("loginError","登录失败！" );
            mode.setViewName("/login");
        }
        return mode;
    }
}
