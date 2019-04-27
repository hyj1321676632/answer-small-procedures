package com.study.system.config;

import com.study.system.entity.UserInfo;
import com.study.system.service.UserInfoSv;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserInfoSv userInfoSv;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Object usernameParam = request.getSession().getAttribute("userId");
        Object pwdParam = request.getSession().getAttribute("userPwd");
        if (StringUtils.isEmpty(usernameParam) || StringUtils.isEmpty(pwdParam)) {
            response.sendRedirect(request.getContextPath() + "/");
            return Boolean.FALSE;
        }else{
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(usernameParam.toString());
            userInfo.setUserPassword(pwdParam.toString());
            if (userInfoSv == null) {
                //解决service为null无法注入问题
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                userInfoSv = (UserInfoSv) factory.getBean("userInfoSv");
            }
            UserInfo checkResult = userInfoSv.checkData(userInfo);
            if(checkResult==null){
                response.sendRedirect(request.getContextPath() + "/");
                return Boolean.FALSE;
            }
        }
        return true;
    }
}
