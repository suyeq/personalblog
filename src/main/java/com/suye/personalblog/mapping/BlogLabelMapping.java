package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-22
 * Time: 16:58
 */
@Repository
public interface BlogLabelMapping {

    @Select("select label_id from blog_label where blog_id=#{id}")
    List<Integer> findLabesIdByBlogId(@Param("id") int id);
}
