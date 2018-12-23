package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 16:11
 */
@Component
public interface BlogMapping {

    @Select("select * from blog order by readnum desc limit 0,5")
    List<Blog> mostPopularBlg();

    @Select("select * from blog where id=#{id}")
    Blog findOneBlog(@Param("id") int id);

    @Select("select count(*) from blog")
    int blogTotal();

    @Select("select * from blog order by create_time desc limit 0,7")
    List<Blog> recentBlogs();

    @Select("select * from blog where istalk=0 order by create_time desc limit 0,7")
    List<Blog> recentBlogNotShuoShuo();

    @Select("select * from blog where istalk=1 order by create_time desc limit 0,7")
    List<Blog> recentBlogIsShuoShuo();

    @Select("select * from blog order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogs(@Param("offset")int offset);

    @Select("select * from blog where istalk=0 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogsNotShuoShuo(@Param("offset")int offset);

    @Select("select * from blog where istalk=1 order by create_time desc limit #{offset},7")
    List<Blog> loadMoreBlogsIsShuoShuo(@Param("offset")int offset);
}
