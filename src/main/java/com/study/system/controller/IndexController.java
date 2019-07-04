package com.study.system.controller;

import com.study.system.entity.TestTitle;
import com.study.system.entity.UserNotes;
import com.study.system.service.UserNotesSv;
import com.study.system.service.UserTitleSv;
import com.study.system.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserTitleSv userTitleSv;
    @Autowired
    private UserNotesSv userNotesSv;

    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mode = new ModelAndView();
        String userId = SessionUtils.getUserId();
        mode.addObject("userId",userId);
        mode.setViewName("/index");
        return mode;
    }

    @RequestMapping(value = "/index/titleManager")
    public ModelAndView titleManager(){
        ModelAndView mode = new ModelAndView();
        String userId = SessionUtils.getUserId();
        List<TestTitle> output = userTitleSv.findFalseTitleList(userId);
        mode.addObject("falseTitleList",output);
        mode.setViewName("/titleManager");
        return mode;
    }

    @RequestMapping(value = "/index/notes")
    public ModelAndView titleNotes(){
        String userId = SessionUtils.getUserId();
        ModelAndView mode = new ModelAndView();
        List<UserNotes> output = userNotesSv.findByUserId(userId);
        mode.addObject("showAllNotes",output);
        mode.setViewName("/notes");
        return mode;
    }

    @RequestMapping(value = "/index/saveNotes")
    public ModelAndView saveNotes(@ModelAttribute("userNotes") @Validated UserNotes userNotes){
        ModelAndView mode = new ModelAndView();
        String userId = SessionUtils.getUserId();
        userNotes.setCreateDate(new Date());
        userNotes.setUserId(userId);
        userNotesSv.save(userNotes);
        List<UserNotes> output = userNotesSv.findByUserId(userId);
        mode.addObject("showAllNotes",output);
        mode.setViewName("/notes");
        return mode;
    }
}
