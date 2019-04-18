package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.study.system.service.ChooseResultSv;
import com.study.system.service.UserInfoSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserInfoSv userInfoSv;
    @Autowired
    private ChooseResultSv chooseResultSv;

    @RequestMapping(value = "/")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/check")
    public ModelAndView save(@ModelAttribute("userInfo") @Validated UserInfo userInfo, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView mode = new ModelAndView();
        UserInfo checkResult = userInfoSv.checkData(userInfo);
        if(checkResult != null){
            String userId = userInfo.getUserId();
            String password = userInfo.getUserPassword();
            //将用户保存到session中
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userPassword", password);
            mode.addObject("userId",userId);
            String role = checkResult.getUserRole();
            if(role.equals("admin")){
                mode.setViewName("/admin");
            }else {
                mode.setViewName("/index");
                //删除答题记录表中数据
                chooseResultSv.delete();
            }
        }else{
            mode.addObject("loginError","登录失败！" );
            mode.setViewName("/login");
        }
        return mode;
    }
}
