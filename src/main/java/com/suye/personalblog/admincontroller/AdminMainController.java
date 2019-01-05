package com.suye.personalblog.admincontroller;

import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.File;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.service.*;
import com.suye.personalblog.tool.BlogMessageConversion;
import com.suye.personalblog.tool.ConmentMessageConversion;
import com.suye.personalblog.tool.PaginationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-01
 * Time: 16:04
 */
@Controller
public class AdminMainController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ConmentService conmentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private FileService fileService;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    @Autowired
    private ConmentMessageConversion conmentMessageConversion;

    @RequestMapping("/admin/index")
    public ModelAndView index(Model model){
        int conmentTotal=conmentService.conmnettotal();
        int blogTotal=blogService.blogTotal();
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.recentBlogs());
        List<ConmentMessageConversion.ConmentMessage> recentCommentList=
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        model.addAttribute("conmentTotal",conmentTotal);
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("recentCommentList",recentCommentList);
        return new ModelAndView("/admin/index","index",model);
    }

    @RequestMapping("/admin/article/publish")
    public ModelAndView writeblog(Model model){
        List<Category> categoryList=categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        return new ModelAndView("/admin/writeblog","write",model);
    }

    @RequestMapping("/admin/blog/{blogId}")
    public ModelAndView modifyBlog(@PathVariable("blogId")int blogId,Model model){
        List<Category> categoryList=categoryService.getAllCategory();
        BlogMessageConversion.BlogMessage blogMessage=
                blogMessageConversion.getOneBlogMessage(blogService.findOneById(blogId));
        model.addAttribute("blogMessage",blogMessage);
        model.addAttribute("categoryList",categoryList);
        return new ModelAndView("/admin/modifyblog","modify",model);
    }

    @RequestMapping("/admin/page")
    public ModelAndView pageManage(Model model){
        return new ModelAndView("/admin/pageManage","pageManage",model);
    }

    @RequestMapping("/admin/blog")
    public ModelAndView articleManage(@RequestParam(name = "page",required = false)String page,
                                                  Model model){
        List<BlogMessageConversion.BlogMessage> blogMessageList=null;
        if (page!=null){
            blogMessageList= blogMessageConversion.getBlogMessageList(blogService.loadMoreRecentBlogs(Integer.parseInt(page)*7));
        }else {
            blogMessageList=blogMessageConversion.getBlogMessageList(blogService.recentBlogs());
        }
        int pagenum= PaginationTool.pageTotal(blogService.blogTotal());
        System.out.println(blogMessageList.size());
        model.addAttribute("blogMessageList",blogMessageList);
        model.addAttribute("pageNum",pagenum);
        return new ModelAndView("/admin/articleManage","articleManage",model);
    }

    @RequestMapping("/admin/attach")
    public ModelAndView fileManage(@RequestParam(name = "page",required = false)String page, Model model){
        List<File> fileList=null;
        if (page!=null){
            fileList=fileService.findFileByOffset(Integer.parseInt(page)*12);
        }else {
            fileList=fileService.findFileByOffset(0);
        }
        int pagenum= PaginationTool.pageTotal(fileService.fileTotal(),"file");
        model.addAttribute("fileList",fileList);
        model.addAttribute("pageNum",pagenum);
        return new ModelAndView("/admin/fileManage","/admin/fileManage",model);
    }

    @RequestMapping("/admin/comments")
    public ModelAndView commentManage(@RequestParam(name = "page",required = false)String page,Model model){
        int pagenum= PaginationTool.pageTotal(conmentService.conmnettotal());
        List<ConmentMessageConversion.ConmentMessage> conmentMessageList=null;
        if (page!=null){
            conmentMessageList= conmentMessageConversion.conmentMessageList(conmentService.findSomeConments(Integer.parseInt(page)*7));
        }else {
            conmentMessageList= conmentMessageConversion.conmentMessageList(conmentService.findSomeConments(0));
        }
        model.addAttribute("conmentMessageList",conmentMessageList);
        model.addAttribute("pageNum",pagenum);
        return new ModelAndView("/admin/commentManage","commentManage",model);
    }

    @RequestMapping("/admin/category")
    public ModelAndView categoryManage(Model model){
        List<Category> categoryList=categoryService.getAllCategory();
        List<Label> labelList=labelService.getAllLabels();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("labelList",labelList);
        return new ModelAndView("/admin/categoryManage","categoryManage",model);
    }

    @RequestMapping("/admin/template")
    public ModelAndView templateManage(Model model){
        return new ModelAndView("/admin/templateManage","templateManage",model);
    }

    @RequestMapping("/admin/themes")
    public ModelAndView themesManage(Model model){
        return new ModelAndView("/admin/themesManage","themesManage",model);
    }

    @RequestMapping("/admin/setting")
    public ModelAndView settingManage(Model model){
        return new ModelAndView("/admin/settingManage","settingManage",model);
    }
}
