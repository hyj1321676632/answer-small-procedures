package com.study.system.dao;

import com.study.system.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,Long> {

    @Query(value = "select * from user_info where user_id = ?1 and user_password = ?2 ",nativeQuery = true)
    UserInfo selectData(String userId, String userPassword);

    @Query(value = " select * from user_info where user_id =?1 ",nativeQuery = true)
    List<UserInfo> selectByUserId(String userId);
}
