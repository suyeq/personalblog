package com.suye.personalblog.mapping;

import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-22
 * Time: 22:37
 */
@Component
public interface BlogCategoryMapping {

    @Select("select category_id from blog_category where blog_id=#{id}")
    List<Integer> findCategoryIdsByBlogId(@Param("id") int id);
}
