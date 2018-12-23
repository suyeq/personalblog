package com.suye.personalblog.controller;

import com.suye.personalblog.model.Conment;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.model.Visitor;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.LabelService;
import com.suye.personalblog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 13:43
 */
@RestController
public class Hello {
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private ConmentService conmentService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/visitor")
    public Visitor getAlls(){
        return  visitorService.findOneById(1);
    }

    @GetMapping("/label")
    public List<Label> getAll(){
        return labelService.getAllLabels();
    }

    @GetMapping("/conment")
    public List<Conment> getAllConment(){
        return conmentService.recentConment();
    }

    @GetMapping("/blog")
    public String getAllblog(){
        return blogService.findOneById(1).getContent();
    }

}
