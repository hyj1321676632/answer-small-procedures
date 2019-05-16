package com.study.system.controller;

import com.study.system.entity.UserInfo;
import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MemcacheController {

    @Autowired
    private MemCachedClient memCachedClient;

    /**
     * memcache缓存
     * 开启缓存的命令: # memcached -d -p 11211 -m 64m -u root
     */
    @RequestMapping("/memcache")
    @ResponseBody
    public String memcache() throws InterruptedException{
        // 放入缓存
        boolean flag = memCachedClient.set("mem", "name");
        // 取出缓存
        Object value = memCachedClient.get("mem");
        System.out.println(value);
        // 3s后过期
        memCachedClient.set("num", "666", new Date(1000));
        memCachedClient.set("num", "666", new Date(System.currentTimeMillis()+3000));
        /*memCachedClient.set("num", "666", new Date(System.currentTimeMillis()+3000));与上面的区别是当设置了这个时间点
        之后，它会以服务端的时间为准，也就是说如果本地客户端的时间跟服务端的时间有差值，这个值就会出现问题。
        例：如果本地时间是20:00:00,服务端时间为20:00:10，那么实际上会是40秒之后这个缓存key失效*/
        //多线程睡眠3s
        Thread.sleep(3000);
        Object key = memCachedClient.get("num");
        System.out.println(key);

        UserInfo info = new UserInfo();
        info.setUserId("huyj123");
        info.setUserPassword("888888");
        memCachedClient.set("info",info);

        memCachedClient.add("info", 123);//错误！无法更新p1的值

        UserInfo obj = (UserInfo)memCachedClient.get("info");
        System.out.println(obj.getUserId()+":"+obj.getUserPassword());
        return "success";
    }
}
