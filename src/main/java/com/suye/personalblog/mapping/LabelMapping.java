package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Label;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 22:38
 */
@Component
public interface LabelMapping {

    @Select("select * from label")
    List<Label> getAllLabels();

    @Select("select * from label where id=#{id}")
    Label findLabelById(@Param("id") int id);

    @Select("select blog_id from blog_label where label_id=#{labelId} limit #{offset},7")
    List<Integer> findBlogIdsByLabelId(@Param("labelId") int labelId,@Param("offset") int offset);

    @Select("select * from label where name=#{name}")
    Label findLabelByLabelName(@Param("name") String name);

    @Insert("insert into blog_label(blog_id,label_id) values(#{blogId},#{labelId})")
    int insertLabeltoBlog(@Param("blogId") int blogId,@Param("labelId") int labelId);

    @Select("select last_insert_id()")
    int lastLabel();

    @Insert("insert into label(name,describ) values(#{name},#{describ})")
    int addLabel(@Param("name") String name,@Param("describ") String describ);

    @Delete("delete from blog_label where label_id=#{labelId}")
    int deleteLabelWithBlogByLabelId(@Param("labelId") int labelId);

    @Delete("delete from label where id=#{labelId}")
    int deleteLabelById(@Param("labelId") int labelId);
}
