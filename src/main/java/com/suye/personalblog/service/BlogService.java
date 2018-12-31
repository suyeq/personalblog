package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogLabelMapping;
import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Label;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 16:21
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapping blogMapping;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 返回最受欢迎的5篇博客
     * @return
     */
    public List<Blog> mostPopularBlog(){
        return blogMapping.mostPopularBlg();
    }

    /**
     * 根据博客的id查询博客信息
     * @param id
     * @return
     */
    public Blog findOneById(int id){
        return blogMapping.findOneBlog(id);
    }

    /**
     * 返回博客的总数
     * @return
     */
    public int blogTotal(){
        return blogMapping.blogTotal();
    }

    /**
     * 返回最近发表的7篇博客
     * @return
     */
    public List<Blog> recentBlogs(){
        return blogMapping.recentBlogs();
    }

    /**
     * 返回最近发表的不是说说的博客
     * @return
     */
    public List<Blog> recentBlogsNotShuoShuo(){
        return blogMapping.recentBlogNotShuoShuo();
    }

    /**
     * 返回最近发表的是说说的7篇博客
     * @return
     */
    public List<Blog> recentBlogsIsShuoShuo(){
        return blogMapping.recentBlogIsShuoShuo();
    }

    /**
     * 加载更多的博客
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentBlogs(int offset){
        return blogMapping.loadMoreBlogs(offset);
    }

    /**
     * 加载更多的不是说说得到博客
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentNotShuoShuo(int offset){
        return blogMapping.loadMoreBlogsNotShuoShuo(offset);
    }

    /**
     * 加载更多的说说
     * @param offset
     * @return
     */
    public List<Blog> loadMoreRecentIsShuoShuo(int offset){
        return blogMapping.loadMoreBlogsIsShuoShuo(offset);
    }

    public int increaseVotenum(int blogId){
        return blogMapping.increaseVotenum(blogId);
    }

    public int increaseCainum(int blogId){
        return blogMapping.increaseCainum(blogId);
    }

    public List<Blog> findAllBlogTimeDesc(){
        return blogMapping.findAllBlogTimeDesc();
    }

    /**
     * 返回归档信息
     * @return
     */
    public Blog findArchive(){
       return blogMapping.findArchive();
    }

    /**
     * 返回友链信息
     * @return
     */
    public Blog findFirends(){
        return blogMapping.findFriends();
    }

    /**
     * 返回关于我的信息
     * @return
     */
    public Blog findAboutMe(){
        return blogMapping.findAboutMe();
    }

    public List<Blog> searchContent(String content){
        return blogMapping.searchContent(content);
    }

    public List<Blog> loadMoreSearch(String content,int offset){
        return blogMapping.loadMoreSearch(content,offset);
    }

    public int increaseReadNum(int blogId){
        return blogMapping.increaseReadNum(blogId);
    }

    public int increaseConmentNum(int blogId){
        return blogMapping.increaseConmentNum(blogId);
    }

    public List<Blog> findBlogsByColumnId(int columnId,int offset){
        List<Integer> blogIdList=columnService.findBlogIdsByColumnId(columnId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        System.out.println("dsadas  "+blogIdList.size());
        return blogList;
    }

    public List<Blog> findBlogsByLabelId(int labelId,int offset){
        List<Integer> blogIdList=labelService.findBlogIdsByLabelId(labelId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        return blogList;
    }

    public List<Blog> findBlogsByCategoryId(int categoryId,int offset){
        List<Integer> blogIdList=categoryService.findBlogIdsByCategoryId(categoryId,offset);
        List<Blog> blogList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            blogList.add(findOneById(blogIdList.get(i)));
        }
        System.out.println("category"+blogList.size());
        return blogList;
    }
}
