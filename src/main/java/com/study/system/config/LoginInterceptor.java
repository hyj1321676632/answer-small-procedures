package com.study.system.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String usernameParam = request.getParameter("userId");
        String pwdParam = request.getParameter("userPassword");
        if (StringUtils.isEmpty(usernameParam) || StringUtils.isEmpty(pwdParam)) {
            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/");
            return Boolean.FALSE;
        }
        return true;
    }
}
