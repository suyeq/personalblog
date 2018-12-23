package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Label;
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
}
