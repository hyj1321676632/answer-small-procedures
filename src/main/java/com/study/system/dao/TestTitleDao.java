package com.study.system.dao;

import com.study.system.entity.TestTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestTitleDao  extends JpaRepository<TestTitle,Long> {

    @Query(value = " select * from test_title where title_id in (select title_id from (select title_id from user_title where user_id = ?1 and \n" +
            "test_result ='false' order by create_date desc) where rownum<= ?2 ) or title_id in(select title_Id from test_title where title_id not in(" +
            "  select title_id from user_title where user_id = ?1) and rownum <= ?3 ) order by ?#{#pageable} ",
            countQuery = "select count(*) from test_title where title_id in (select title_id from (select title_id from user_title where user_id = ?1 and \n" +
                    "test_result ='false' order by create_date desc) where rownum<= ?2 ) or title_id in(select title_Id from test_title where title_id not in(" +
                    "  select title_id from user_title where user_id = ?1) and rownum <= ?3 ) ", nativeQuery = true)
    Page<TestTitle> findByFalseAndNew(String userId,int errorCount,int newCount,Pageable pageable);


    @Query(value = "select * from test_title where title_id not in (select title_id from user_title where user_id = :userId ) and rownum <= :titleCount order by ?#{#pageable}",
            countQuery="select count(*) from test_title where title_id not in (select title_id from user_title where user_id =:userId) and rownum <= :titleCount",
            nativeQuery = true)
    Page<TestTitle> findByOnlyNew(@Param("userId")String userId, @Param("titleCount")String titleCount, Pageable pageable);

    @Query(value = "select * from test_title where title_id in (select title_id from user_title where user_id =:userId and test_result = 'false') and rownum <= :titleCount order by ?#{#pageable}",
            countQuery="select count(*) from test_title where title_id in (select title_id from user_title where user_id =:userId and test_result = 'false') and rownum <= :titleCount ",
            nativeQuery = true)
    Page<TestTitle> findByOnlyFalse(@Param("userId")String userId, @Param("titleCount")String titleCount, Pageable pageable);

    @Query(value = " select * from test_title where title_id  in (select title_id  from user_title where test_result='false' and user_id =?1 )",nativeQuery = true)
    List<TestTitle> findFalseTitleList(String userId);

    @Query(value = "select * from test_title where title_id =?1",nativeQuery = true)
    TestTitle findByTitleId(long titleId);

    @Query(value = " select * from test_title where  rownum <= ?1 order by ?#{#pageable}",
            countQuery = " select count(*) from test_title where rownum <= ?1 ",nativeQuery = true)
    Page<TestTitle> findByRownum(String titleCount,Pageable pageable);

}
