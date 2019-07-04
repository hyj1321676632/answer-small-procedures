package com.study.system.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class SessionUtils {

    static HttpSession httpSession = null;

    /**
     * 存放用户账号和密码
     * @param userId
     * @param userPwd
     * @param request
     */
    public static void setSession(String userId, String userPwd, HttpServletRequest request){
        httpSession = request.getSession(true);
        httpSession.setAttribute("userId",userId);
        httpSession.setAttribute("userPwd",userPwd);
    }

    /**
     * 获取session中的用户账号
     * @return
     */
    public static String getUserId(){
        String userId = httpSession.getAttribute("userId").toString();
        return userId;
    }

    /**
     * 获取用户账号和密码
     * @return
     */
    public Map<String, String> getIdAndPwd(){
        Map<String, String> map = new HashMap<String, String>();
        String userId = httpSession.getAttribute("userId").toString();
        String userPwd = httpSession.getAttribute("userPwd").toString();
        map.put("userId",userId);
        map.put("userPwd",userPwd);
        return map;
    }

    /**
     * 销毁session
     */
    public static void destroySesson(){
        if(httpSession != null)
            httpSession.invalidate();
    }
}
