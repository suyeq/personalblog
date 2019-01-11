package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.mapping.ColumnMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private BlogMapping blogMapping;

    public List<Column> getAllColumn(){
        return columnMapping.getAllColumn();
    }

    /**
     * 根据专栏id查找已发布且不为说说的博客
     * @param columnId
     * @param offset
     * @return
     */
    public List<Integer> findBlogIdsByColumnId(int columnId,int offset){
        List<Integer> blogIdsList=columnMapping.findBlogIdsByColumnId(columnId,offset);
        List<Integer> newBlogIdList=new ArrayList<>();
        for (int i=0;i<blogIdsList.size();i++){
            Blog blog=blogMapping.findOneBlog(blogIdsList.get(i));
            if ((blog.getIsPublish()==1)&&(blog.getIsTalk()!=1)){
                newBlogIdList.add(blogIdsList.get(i));
            }
        }
        return newBlogIdList;
    }

    public Column findOneByColumnId(int columnId){
        return columnMapping.findOneByColumnId(columnId);
    }
}
