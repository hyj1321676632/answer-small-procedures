package com.study.system.service;

import com.study.system.dao.TestTitleDao;
import com.study.system.entity.TestTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestTitleSv {

    @Autowired
    private TestTitleDao testTitleDao;

    public List<TestTitle> find(){
        List<TestTitle> output = testTitleDao.findAll();
        return output;
    }

    public void save(TestTitle testTitle){
        testTitleDao.save(testTitle);
    }
}
