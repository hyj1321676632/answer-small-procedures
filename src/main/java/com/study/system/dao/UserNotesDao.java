package com.study.system.dao;

import com.study.system.entity.UserNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserNotesDao extends JpaRepository<UserNotes,Long> {

    @Query(value = " select * from user_notes where user_id = ?1 ",nativeQuery = true)
    List<UserNotes> findByUserId(String userId);
}
