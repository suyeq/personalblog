package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogLabelMapping;
import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Label;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
