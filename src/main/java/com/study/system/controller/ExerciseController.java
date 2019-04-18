package com.study.system.controller;

import com.study.system.entity.AnswerResult;
import com.study.system.entity.ChooseResult;
import com.study.system.entity.TestTitle;
import com.study.system.entity.UserTitle;
import com.study.system.service.ChooseResultSv;
import com.study.system.service.UserTitleSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ExerciseController {

    @Autowired
    private UserTitleSv userTitleSv;
    @Autowired
    private ChooseResultSv chooseResultSv;

    @RequestMapping(value = "/exercise")
    public String index() {
        return "exercise";
    }

//    @RequestMapping(value = "/choose/titles")
//    public ModelAndView changeTitles(@RequestParam(value = "titleSource", required = false, defaultValue = "onlyNew") String titleSource,
//                                     @RequestParam(value = "titleCount", required = false, defaultValue = "10") String titleCount,
//                                     HttpServletRequest request, HttpSession httpSession) {
//        ModelAndView mode = new ModelAndView();
//        String userId = httpSession.getAttribute("userId").toString();
//        List<TestTitle> output =null;
//        if (titleSource.equals("falseAndNew")) {
//            output = userTitleSv.findByfalseAndNew(userId,titleCount);
//        } else if (titleSource.equals("onlyNew")) {
//            output = userTitleSv.findByOnlyNew(userId,titleCount);
//        } else if (titleSource.equals("onlyFalse")) {
//            output = userTitleSv.findByOnlyFalse(userId,titleCount);
//        }
//        mode.addObject("testTitleList", output);
//        mode.setViewName("exercise");
//        return mode;
//    }

    @RequestMapping(value = "/exercise/submit")
    public String exerciseSubmit(@ModelAttribute("titleResult") ChooseResult chooseResult,Model model,HttpSession httpSession) {
        String userId = httpSession.getAttribute("userId").toString();
        if(chooseResult.getChooseAnswer() != null){
            chooseResultSv.findChooseResult(chooseResult.getTitleId(),chooseResult.getChooseAnswer(),userId);
        }else{
            chooseResultSv.findChooseResult(chooseResult.getTitleId(),"未选择",userId);
        }
        model.addAttribute("testTitleList",httpSession.getAttribute("testTitleList"));
        model.addAttribute("pageNum",httpSession.getAttribute("pageNum"));
        model.addAttribute("totalPages",httpSession.getAttribute("totalPages"));
        model.addAttribute("totalElements",httpSession.getAttribute("totalElements"));
        return "exercise";
    }

    @RequestMapping(value = "/choose/titles")
    public String changeTitles(@RequestParam(value = "titleSource", required = false) String titleSource,
                                     @RequestParam(value = "titleCount", required = false) String titleCount,
                                     @RequestParam(value="pageNum",defaultValue = "0") String pageNum,Model model,
                                     HttpServletRequest request, HttpSession httpSession) {
        String userId = httpSession.getAttribute("userId").toString();

        if(titleSource != null){
            httpSession.setAttribute("titleSource", titleSource);
        }else{
            titleSource = httpSession.getAttribute("titleSource").toString();
        }
        if(titleCount != null){
            httpSession.setAttribute("titleCount", titleCount);
        }else{
            titleCount = httpSession.getAttribute("titleCount").toString();
        }
        Page<TestTitle> pages =null;
        if (titleSource.equals("falseAndNew")) {
            pages = userTitleSv.findByFalseAndNew(userId,titleCount,Integer.parseInt(pageNum));
        } else if (titleSource.equals("onlyNew")) {
            pages = userTitleSv.findByOnlyNew(userId,titleCount,Integer.parseInt(pageNum));
            if(pages.getContent().size() == 0){
                model.addAttribute("nullNewTitle","题库中已经没有新题了，请重新选择！");
                return "index";
            }
        } else if (titleSource.equals("onlyFalse")) {
            List<UserTitle> output = userTitleSv.findByUserId(userId);
            if(output ==null || output.size()==0){
                model.addAttribute("nullErrorTitle","您目前还没有错题记录，请重新选择！");
                return "index";
            }
            pages = userTitleSv.findByOnlyFalse(userId,titleCount,Integer.parseInt(pageNum));
        }
        if(pageNum==null){
            pageNum="0";
        }
        int pagenum=Integer.parseInt(pageNum);
//        model.addAttribute("testTitleList",pages.getContent().get(Integer.parseInt(pageNum)));
        model.addAttribute("testTitleList",pages.getContent().get(0));
        model.addAttribute("pageNum",pagenum);
        model.addAttribute("totalPages",pages.getTotalPages());
        model.addAttribute("totalElements",pages.getTotalElements());
        //存入sesssion
//        httpSession.setAttribute("testTitleList", pages.getContent().get(Integer.parseInt(pageNum)));
        httpSession.setAttribute("testTitleList", pages.getContent().get(0));
        httpSession.setAttribute("pageNum", pagenum);
        httpSession.setAttribute("totalPages", pages.getTotalPages());
        httpSession.setAttribute("totalElements", pages.getTotalElements());
        return "exercise";
    }


    @RequestMapping(value = "/exercise/over")
    public ModelAndView exerciseOver(HttpSession httpSession){
        ModelAndView model = new ModelAndView();
        String userId = httpSession.getAttribute("userId").toString();
        List<AnswerResult> output = chooseResultSv.checkData(userId);
        model.addObject("answerResultList",output);
        model.setViewName("/answer");
        return model;
    }

}
