package com.suye.personalblog.mapping;

import com.suye.personalblog.model.Column;
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
    public List<Column> getAllColumn();
}
