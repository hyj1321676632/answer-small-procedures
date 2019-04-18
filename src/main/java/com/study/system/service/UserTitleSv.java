package com.study.system.service;

import com.study.system.dao.TestTitleDao;
import com.study.system.dao.UserTitleDao;
import com.study.system.entity.TestTitle;
import com.study.system.entity.UserTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserTitleSv {

    @Autowired
    private TestTitleDao testTitleDao;
    @Autowired
    private UserTitleDao userTitleDao;
    @Autowired
    private UserTitleSv userTitleSv;

    /**
     * 算法核心代码
     * @param userId
     * @param titleCount
     * @param pageNum
     * @return
     */
    public Page<TestTitle> findByFalseAndNew(String userId,String titleCount,int pageNum){
        List<UserTitle> userTitleList = userTitleSv.findByUserId(userId);
        Pageable pageable = new PageRequest(pageNum, 1);
         /*
            两种情况：一种是还没有做题导致表中没有错题信息，另一种是做的题目全都是对的,
            针对这种情况应该推送暂时还没有做过的题目
         */
        if(userTitleList==null || userTitleList.size()==0){
            Page<TestTitle> output = testTitleDao.findByRownum(titleCount,pageable);
            return output;
        }
        int errorCount = Integer.parseInt(titleCount)*3/5;
        int newCount = Integer.parseInt(titleCount)-errorCount;
        Page<TestTitle> output = testTitleDao.findByFalseAndNew(userId,errorCount,newCount,pageable);
        return output;
    }

    public Page<TestTitle> findByOnlyNew(String userId,String titleCount,int pageNum) {
        Pageable pageable = new PageRequest(pageNum, 1);
        Page<TestTitle> output = testTitleDao.findByOnlyNew(userId,titleCount,pageable);
        return output;
    }

    public Page<TestTitle> findByOnlyFalse(String userId,String titleCount,int pageNum){
        Pageable pageable = new PageRequest(pageNum, 1);
        Page<TestTitle> output = testTitleDao.findByOnlyFalse(userId,titleCount,pageable);
        return output;
    }

    public List<TestTitle> findFalseTitleList(String userId){
        List<TestTitle> output = testTitleDao.findFalseTitleList(userId);
        return output;
    }

    /**
     * 更新用户题目关系表
     * @param userId
     * @param titleId
     * @param testResult
     */
    public void updateUserTitle(String userId,long titleId,String testResult){
        UserTitle userTitle = userTitleDao.findByIds(userId,titleId);
        if(userTitle != null){
            userTitleDao.updateUserTitle(testResult,new Date(),userId,titleId);
        }else{
            UserTitle intput = new UserTitle();
            intput.setCreateDate(new Date());
            intput.setTestResult(testResult);
            intput.setTitleId(titleId);
            intput.setUserId(userId);
            userTitleDao.save(intput);
        }
    }

    public List<UserTitle> findByUserId(String userId){
        List<UserTitle> output = userTitleDao.findByUserId(userId);
        return output;
    }

    public void save(UserTitle userTitle){
        userTitleDao.save(userTitle);
    }
}
