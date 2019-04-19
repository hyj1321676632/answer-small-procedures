package com.study.system.util;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class SessionUtil {

    /**
     * 存放用户的用户名和密码
     * @param userId
     * @param userPwd
     * @param httpSession
     */
    public static void setSession(String userId, String userPwd, HttpSession httpSession){
        httpSession.setAttribute("userId",userId);
        httpSession.setAttribute("userPwd",userPwd);
    }

    /**
     * 获取session中的用户名和密码
     * @param httpSession
     * @return
     */
    public static Map getSession(HttpSession httpSession){
        Map<String,String> map = new HashMap<String,String>();
        String userId = httpSession.getAttribute("userId").toString();
        String userPwd = httpSession.getAttribute("userPwd").toString();
        map.put("userId",userId);
        map.put("userPwd",userPwd);
        return map;
    }

    /**
     * 销毁session
     * @param httpSession
     */
    public void destroySesson(HttpSession httpSession){
        httpSession.invalidate();
    }
}
