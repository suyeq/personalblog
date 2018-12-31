package com.suye.personalblog.service;

import com.suye.personalblog.mapping.ColumnMapping;
import com.suye.personalblog.model.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 10:18
 */
@Service
public class ColumnService {

    @Autowired
    private ColumnMapping columnMapping;

    public List<Column> getAllColumn(){
        return columnMapping.getAllColumn();
    }

    public List<Integer> findBlogIdsByColumnId(int columnId,int offset){
        return columnMapping.findBlogIdsByColumnId(columnId,offset);
    }

    public Column findOneByColumnId(int columnId){
        return columnMapping.findOneByColumnId(columnId);
    }
}
