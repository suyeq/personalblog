package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 22:56
 */
@Component
public interface CategoryMapping {

    @Select("select * from category")
    List<Category> getAllCategory();

    @Select("select * from category where id=#{id}")
    Category findOneById(@Param("id") int id);

    @Select("select blog_id from blog_category where category_id=#{categoryId} limit #{offset},7")
    List<Integer> findBlogIdsByCategoryId(@Param("categoryId") int labelId,@Param("offset") int offset);

}
