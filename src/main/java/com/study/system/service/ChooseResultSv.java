package com.study.system.service;

import com.study.system.dao.ChooseResultDao;
import com.study.system.dao.TestTitleDao;
import com.study.system.entity.AnswerResult;
import com.study.system.entity.ChooseResult;
import com.study.system.entity.TestTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ChooseResultSv {

    @Autowired
    private ChooseResultDao chooseResultDao;
    @Autowired
    private TestTitleDao testTitleDao;
    @Autowired
    private UserTitleSv userTitleSv;

    public void save(ChooseResult chooseResult){
        chooseResultDao.save(chooseResult);
    }

    public void delete(String userId){
        chooseResultDao.delete(userId);
    }

    /**
     * 判断表中是否已经存在这道题的结果
     * @param titleId
     * @param chooseAnswer
     */
    public void findChooseResult(long titleId,String chooseAnswer,String userId){
        ChooseResult output = chooseResultDao.findByTitleId(titleId);
        if(output != null){
            chooseResultDao.update(titleId,chooseAnswer);
        }else{
            ChooseResult result = new ChooseResult();
            result.setTitleId(titleId);
            result.setChooseAnswer(chooseAnswer);
            result.setUserId(userId);
            chooseResultDao.save(result);
        }
    }

    /**
     * 校验题目正确性
     * @param userId
     * @return
     */
    public List<AnswerResult> checkData(String userId){
        List<ChooseResult> chooseResults = chooseResultDao.findByUserId(userId);
        List<AnswerResult> output = new ArrayList<AnswerResult>();
        for(int i=0;i<chooseResults.size();i++){
            ChooseResult result = chooseResults.get(i);
            long titleId = result.getTitleId();
            String chooseAnswer = result.getChooseAnswer();
            TestTitle testTitle = testTitleDao.findByTitleId(titleId);
            String titleAnswer = testTitle.getTitleAnswer();
            String testResult = null;
            if(titleAnswer.equals(chooseAnswer)){
                testResult="正确";
            }else{
                testResult="错误";
            }
            AnswerResult answerResult = new AnswerResult();
            answerResult.setTitleHeader(testTitle.getTitleHeader());
            answerResult.setOptionA(testTitle.getOptionA());
            answerResult.setOptionB(testTitle.getOptionB());
            answerResult.setOptionC(testTitle.getOptionC());
            answerResult.setOptionD(testTitle.getOptionD());
            answerResult.setTitleAnswer(testTitle.getTitleAnswer());
            answerResult.setParseText(testTitle.getParseText());
            answerResult.setAnswer(result.getChooseAnswer());
            answerResult.setResult(testResult);
            answerResult.setTitleId(i+1);
            output.add(answerResult);
            if(titleAnswer.equals(chooseAnswer)){
                testResult="true";
            }else{
                testResult="false";
            }
            userTitleSv.updateUserTitle(userId,titleId,testResult);
        }
        return output;
    }
}
