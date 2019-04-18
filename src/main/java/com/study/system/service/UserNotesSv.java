package com.study.system.service;

import com.study.system.dao.UserInfoDao;
import com.study.system.dao.UserNotesDao;
import com.study.system.entity.UserInfo;
import com.study.system.entity.UserNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserNotesSv {

    @Autowired
    private UserNotesDao userNotesDao;

    public void save(UserNotes userNotes){
        userNotesDao.save(userNotes);
    }

    public List<UserNotes> findAll(){
        return userNotesDao.findAll();
    }

    public List<UserNotes> findByUserId(String userId){
        return userNotesDao.findByUserId(userId);
    }
}
