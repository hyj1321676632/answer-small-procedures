package com.study.system.dao;

import com.study.system.entity.UserNotes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserNotesDao extends JpaRepository<UserNotes,Long> {

}
