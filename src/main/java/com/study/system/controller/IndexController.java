package com.study.system.controller;

import com.study.system.entity.TestTitle;
import com.study.system.entity.UserInfo;
import com.study.system.entity.UserNotes;
import com.study.system.service.ChooseResultSv;
import com.study.system.service.UserNotesSv;
import com.study.system.service.UserTitleSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserTitleSv userTitleSv;
    @Autowired
    private UserNotesSv userNotesSv;
    @Autowired
    private ChooseResultSv chooseResultSv;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpSession httpSession){
        ModelAndView mode = new ModelAndView();
        String userId = httpSession.getAttribute("userId").toString();
        chooseResultSv.delete();
        mode.addObject("userId",userId);
        mode.setViewName("/index");
        return mode;
    }

    @RequestMapping(value = "/index/titleManager")
    public ModelAndView titleManager(HttpServletRequest request, HttpSession httpSession){
        ModelAndView mode = new ModelAndView();
        String userId = httpSession.getAttribute("userId").toString();
        List<TestTitle> output = userTitleSv.findFalseTitleList(userId);
        mode.addObject("falseTitleList",output);
        mode.setViewName("/titleManager");
        return mode;
    }

    @RequestMapping(value = "/index/notes")
    public ModelAndView titleNotes(){
        ModelAndView mode = new ModelAndView();
        List<UserNotes> output = userNotesSv.findAll();
        mode.addObject("showAllNotes",output);
        mode.setViewName("/notes");
        return mode;
    }

    @RequestMapping(value = "/index/saveNotes")
    public ModelAndView saveNotes(@ModelAttribute("userNotes") @Validated UserNotes userNotes){
        ModelAndView mode = new ModelAndView();
        userNotes.setCreateDate(new Date());
        userNotesSv.save(userNotes);
        List<UserNotes> output = userNotesSv.findAll();
        mode.addObject("showAllNotes",output);
        mode.setViewName("/notes");
        return mode;
    }
}
