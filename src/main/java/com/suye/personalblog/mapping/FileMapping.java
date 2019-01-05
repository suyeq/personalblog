package com.suye.personalblog.mapping;

import com.suye.personalblog.model.File;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-04
 * Time: 12:32
 */
@Component
public interface FileMapping {
    @Select("select * from file limit #{offset},12")
    List<File> findFileByOffset(@Param("offset") int offset);

    @Delete("delete from file where id=#{id}")
    int deleteFileById(@Param("id") int id);

    @Insert("insert into file(name,url) values(#{name},#{url})")
    int addFile(@Param("name") String name,@Param("url") String url);

    @Update("update file set url=#{url} where id=#{id}")
    int updateFileUrl(@Param("id") int id,@Param("url") String url);

    @Select("select last_insert_id()")
    int lastFile();

    @Select("select count(*) from file")
    int fileTotal();

    @Select("select id from file where name=#{fileName}")
    int findFileIdByFileName(@Param("fileName") String fileName);

    @Select("select * from file where id=#{id}")
    File findOneById(@Param("id") int id);
}
