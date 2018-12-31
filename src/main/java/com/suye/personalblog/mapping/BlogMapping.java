package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update blog set votenum=votenum+1 where id=#{blogId}")
    int increaseVotenum(@Param("blogId") int blogId);

    @Update("update blog set cainum=cainum+1 where id=#{blogId}")
    int increaseCainum(@Param("blogId") int blogId);

    @Select("select * from blog order by create_time desc")
    List<Blog> findAllBlogTimeDesc();

    @Select("select * from blog where istalk=3")
    Blog findArchive();

    @Select("select * from blog where istalk=4")
    Blog findFriends();

    @Select("select * from blog where istalk=5")
    Blog findAboutMe();

    @Select("select * from blog where content like CONCAT('%',#{content},'%') or title like CONCAT('%',#{content},'%') limit 0,7")
    List<Blog> searchContent(@Param("content") String content);

    @Select("select * from blog where content like CONCAT('%',#{content},'%') or title like CONCAT('%',#{content},'%') limit #{offset},7")
    List<Blog> loadMoreSearch(@Param("content") String content,@Param("offset") int offset);

    @Update("update blog set readnum=readnum+1 where id=#{blogId}")
    int increaseReadNum(int blogId);

    @Update("update blog set conmentnum=conmentnum+1 where id=#{blogId}")
    int increaseConmentNum(int blogId);
}
