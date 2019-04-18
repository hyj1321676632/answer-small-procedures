package com.study.system.dao;

import com.study.system.entity.UserTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface UserTitleDao  extends JpaRepository<UserTitle,Long> {

    @Query(value = " select * from user_title where user_id =?1 and title_id =?2 ",nativeQuery = true)
    UserTitle findByIds(String userId,long titleId);

    @Query(value = "select * from user_title where user_id =?1 and test_result='false' ",nativeQuery = true)
    List<UserTitle> findByUserId(String userId);

    @Modifying
    @Query(value = " update user_title set test_result =?1, create_date =?2 where user_id = ?3 and title_id = ?4",nativeQuery = true)
    void updateUserTitle(String testResult, Date date,String userId,long titleId);
}
