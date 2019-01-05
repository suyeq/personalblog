package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.Column;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.CategoryService;
import com.suye.personalblog.service.ColumnService;
import com.suye.personalblog.service.LabelService;
import com.suye.personalblog.tool.BlogMessageConversion;
import com.suye.personalblog.tool.PaginationTool;
import com.suye.personalblog.tool.RunningTrackStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-30
 * Time: 15:42
 */
@Controller
public class ColumnLabelAndCategoryController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    @Autowired
    private PaginationTool paginationTool;

    @RequestMapping("/column/{columnId}")
    public ModelAndView showColumn(@PathVariable("columnId")int columnId, Model model){
//        System.out.println();
        paginationTool.reset();
        paginationTool.setCategoryColumn();
        paginationTool.setColumnId(columnId);
        String loadMoreHref="/loadMoreColumn";
        Column column=columnService.findOneByColumnId(columnId);
        RunningTrackStack.addRunningStack(column.getName(),"/column/"+columnId);
        List<RunningTrackStack.RunningTrack> runningTrackList=RunningTrackStack.getRunningTrackStack();
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByColumnId(columnId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/loadMoreColumn")
    public ModelAndView loadMoreBlogsColumn(Model model){
        String loadMoreHref="/loadMoreColumn";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList());
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/label/{labelId}")
    public ModelAndView showLabel(@PathVariable("labelId")int labelId,Model model){
        paginationTool.reset();
        paginationTool.setCategoryLabel();
        paginationTool.setLabelId(labelId);
        String loadMoreHref="/loadMoreLabel";
        Label label =labelService.findLabelById(labelId);
        RunningTrackStack.addRunningStack(label.getName(),"/label/"+labelId);
        List<RunningTrackStack.RunningTrack> runningTrackList=RunningTrackStack.getRunningTrackStack();
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByLabelId(labelId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/loadMoreLabel")
    public ModelAndView loadMoreBlogsLabel(Model model){
        String loadMoreHref="/loadMoreLabel";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList());
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }


    @RequestMapping("/category/{categoryId}")
    public ModelAndView showCategory(@PathVariable("categoryId")int categoryId,Model model){
        paginationTool.reset();
        paginationTool.setCategoryCategory();
        paginationTool.setCategoryId(categoryId);
        String loadMoreHref="/loadMoreCategory";
        Category category =categoryService.findOneById(categoryId);
        RunningTrackStack.addRunningStack(category.getName(),"/category/"+categoryId);
        List<RunningTrackStack.RunningTrack> runningTrackList=RunningTrackStack.getRunningTrackStack();
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByCategoryId(categoryId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/loadMoreCategory")
    public ModelAndView loadMoreBlogsCategory(Model model){
        String loadMoreHref="/loadMoreCategory";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList());
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }
}
