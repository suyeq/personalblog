package com.suye.personalblog.controller;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.Column;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.service.*;
import com.suye.personalblog.tool.BlogMessageConversion;
import com.suye.personalblog.tool.ConmentMessageConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 21:27
 */
@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ConmentService conmentService;
    @Autowired
    private ConmentMessageConversion conmentMessageConversion;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    //加载更多的索引
    private int loadMore=0;

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView allMessage(Model model){
        System.out.println("lall");
        int blogTotal=blogService.blogTotal();
        List<Label> labelList=labelService.getAllLabels();
        List<Category> categoryList=categoryService.getAllCategory();
        List<Column> columnList=columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList=
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList=
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());

        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.recentBlogs());
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("labelList",labelList);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("columnList",columnList);
        model.addAttribute("popularBlogList",popularBlogList);
        model.addAttribute("recentConmentList",recentConmentList);
        model.addAttribute("recentBlogList",recentBlogList);
        return new ModelAndView("index","allMessge",model);
    }

    @GetMapping("/blogs")
    public ModelAndView indexBlog(Model model){
        System.out.println("blogs");
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.recentBlogsNotShuoShuo());
        model.addAttribute("recentBlogList",recentBlogList);
        return new ModelAndView("front/indexblog","allMessge",model);

    }


    @GetMapping("/shuoshuos")
    public ModelAndView indexShuoShuo(Model model){
        System.out.println("shuosshuos");
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.recentBlogsIsShuoShuo());
        model.addAttribute("recentBlogList",recentBlogList);
        return new ModelAndView("front/indexshuoshuo","allMessge",model);

    }


}
