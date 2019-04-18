package com.study.system.dao;

import com.study.system.entity.ChooseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ChooseResultDao extends JpaRepository<ChooseResult,Long> {

    @Query(value = " select * from choose_result where title_id =?1",nativeQuery = true)
    ChooseResult findByTitleId(long titleId);

    @Modifying
    @Query(value = " update choose_result set choose_answer = ?2 where title_id =?1 ",nativeQuery = true)
    void update(long titleId,String chooseAnswer);

    @Modifying
    @Query(value = " delete  from  choose_result",nativeQuery = true)
    void delete();
}
