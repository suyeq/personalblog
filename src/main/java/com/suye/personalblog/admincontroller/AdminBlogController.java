package com.suye.personalblog.admincontroller;

import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.CategoryService;
import com.suye.personalblog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-01
 * Time: 21:34
 */
@Controller
public class AdminBlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LabelService labelService;
    /**
     * 新增博客
     * @param categoties 分类
     * @param stauts 博客状态
     * @param allowComment 允许评论
     * @param isShuoShuo  是否说说
     * @param allowFeed  是否订阅
     * @param content 文章内容
     * @param fmtType  文章编辑类型
     * @param title  标题
     * @param slug  自定义路径
     * @param tags  标签
     * @ thumbImg  图片地址
     * @return
     */
    @RequestMapping("/admin/blog/publish")
    @ResponseBody
    public String addBlog(@RequestParam("categories") List<String> categoties,
                          @RequestParam("status")String stauts,
                          @RequestParam("allowComment")boolean allowComment,
                          @RequestParam("isShuoShuo")boolean isShuoShuo,
                          @RequestParam("allowFeed")boolean allowFeed,
                          @RequestParam("content")String content,
                          @RequestParam("fmtType")String fmtType,
                          @RequestParam("title")String title,
                          @RequestParam("slug")String slug,
                          @RequestParam("tags")List<String> tags,
                          @RequestParam("thumbImg")String img){
        int isPublish=0;
        if (stauts.equals("publish")){
            isPublish=1;
        }
        if (categoties.size()==0){
            categoties.add("默认分类");
        }
        int blogId=blogService.addBlog(title,img,slug,content,boolConversion(isShuoShuo),boolConversion(allowComment),isPublish);
        categoryService.insertCategorytoBlog(blogId,categoties);
        labelService.insertLabeltoBlog(blogId,tags);
        return "{\n" + "  \"success\":\"保存成功\"\n" + "}";
    }

    @RequestMapping("/admin/blog/modify")
    @ResponseBody
    public String modifyBlog(@RequestParam("categories") List<String> categoties,
                             @RequestParam("status")String stauts,
                             @RequestParam("allowComment")boolean allowComment,
                             @RequestParam("isShuoShuo")boolean isShuoShuo,
                             @RequestParam("allowFeed")boolean allowFeed,
                             @RequestParam("content")String content,
                             @RequestParam("fmtType")String fmtType,
                             @RequestParam("title")String title,
                             @RequestParam("slug")String slug,
                             @RequestParam("tags")List<String> tags,
                             @RequestParam("thumbImg")String img,
                             @RequestParam("cid")int blogId){
        int isPublish=0;
        if (stauts.equals("publish")){
            isPublish=1;
        }
        System.out.println("djasjdjaj");
        blogService.deleteBlogAndLabelByBlogId(blogId);
        blogService.deleteBlogAndCategoryByBlogId(blogId);
        categoryService.insertCategorytoBlog(blogId,categoties);
        labelService.insertLabeltoBlog(blogId,tags);
        if (blogService.modifyBlog(title,img,slug,content,boolConversion(isShuoShuo),boolConversion(allowComment),isPublish,blogId)>0){
            return "{\n" + "  \"success\":\"修改成功\"\n" + "}";
        }else {
            return "{\n" + "  \"msg\":\"网络错误\"\n" + "}";
        }
    }

    @RequestMapping("/admin/blog/delete")
    @ResponseBody
    public String deleteBlog(@RequestParam("cid")int id){
        if (blogService.deleteBlog(id)>0){
            return "{\n" + "  \"success\":\"删除成功\"\n" + "}";
        }else {
            return "{\n" + "  \"message\":\"网络错误\"\n" + "}";
        }
    }













    private int boolConversion(boolean bool){
        if (bool){
            return 1;
        }
        return 0;
    }
}


