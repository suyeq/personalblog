package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Column;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 10:16
 */
@Component
public interface ColumnMapping {

    @Select("select * from columns")
    List<Column> getAllColumn();

    @Select("select * from columns where id=#{columnId}")
    Column findOneByColumnId(@Param("columnId") int columnId);

    @Select("select blog_id from blog_column where column_id=#{columnId} limit #{offset},7")
    List<Integer> findBlogIdsByColumnId(@Param("columnId") int columnId,@Param("offset") int offset);

}
