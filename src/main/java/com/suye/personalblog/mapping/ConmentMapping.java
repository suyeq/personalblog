package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Conment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 21:57
 */
@Component
public interface ConmentMapping {

    @Select("select * from conment order by conment_time desc limit 0,5")
    List<Conment> recentConment();

    @Select("select * from conment where id=#{id}")
    Conment findOneConmentById(@Param("id") int id);

    @Select("select * from conment where blog_id=#{blogId} and conment_parent_id=0 order by conment_time desc limit #{offset},5")
    List<Conment> findConmentsIsParentByBlogId(@Param("blogId") int blogId,@Param("offset")int offset);

    @Select("select * from conment order by conment_time desc limit #{offset},7")
    List<Conment> findSomeConments(@Param("offset") int offset);

    @Select("select * from conment where conment_parent_id=#{conmentParentId}")
    List<Conment> findConmentsByConmentPrentId(@Param("conmentParentId") int conmentParentId);

    @Insert("insert into conment(content,conment_time,visitor_id,blog_id,conment_parent_id,votenum,cainum) values(#{content},NOW(),#{visitor_id},#{blog_id},#{conment_parent_id},0,0)")
    int addConment(@Param("content") String content,@Param("visitor_id") int visitor_id,@Param("blog_id") int blog_id,@Param("conment_parent_id") int conment_parent_id);

    @Select("select * from conment where id = (select max(id) from conment)")
    Conment findLastConment();

    @Select("select count(*) from conment where conment_parent_id=0 and blog_id=#{blogId}")
    int conmnetTotal(@Param("blogId") int blogId);

    @Update("update conment set votenum=votenum+1 where id=#{conmentId}")
    int increaseConmentZan(@Param("conmentId") int conmentId);

    @Update("update conment set cainum=cainum+1 where id=#{conmentId}")
    int increaseConmentCai(@Param("conmentId")int conmentId);

    @Select("select count(*) from conment")
    int conmnettotal();

    @Delete("delete from conment where id=#{id}")
    int deleteCommentById(@Param("id") int id);
}
