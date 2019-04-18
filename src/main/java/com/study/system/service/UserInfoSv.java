package com.study.system.service;

import com.study.system.dao.UserInfoDao;
import com.study.system.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserInfoSv {

    @Autowired
    private UserInfoDao userInfoDao;

    public void saveUserInfo(UserInfo userInfo ){
        userInfo.setCreateDate(new Date());
        userInfo.setUserRole("general");
        userInfoDao.save(userInfo);
    }

    public UserInfo checkData(UserInfo userInfo){
        String userId = userInfo.getUserId();
        String password = userInfo.getUserPassword();
        UserInfo output=userInfoDao.selectData(userId,password);
        return output;
    }

    public boolean checkByUserId(String userId){
        List<UserInfo> output = userInfoDao.selectByUserId(userId);
        if(output != null && output.size()>0){
            return true;
        }
        return false;
    }

    public List<UserInfo> findAll(){
        List<UserInfo> output = userInfoDao.findAll();
        return output;
    }

    public void delete(long id){
        userInfoDao.deleteById(id);
    }

    public UserInfo selectByUserId(String userId){
        List<UserInfo> output = userInfoDao.selectByUserId(userId);
        if(output != null && output.size()>0){
            return output.get(0);
        }
        return null;
    }
}
