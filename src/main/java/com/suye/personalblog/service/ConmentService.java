package com.suye.personalblog.service;

import com.suye.personalblog.mapping.ConmentMapping;
import com.suye.personalblog.model.Conment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 22:02
 */
@Service
public class ConmentService {

    @Autowired
    private ConmentMapping conmentMapping;

    public List<Conment> recentConment(){
        return conmentMapping.recentConment();
    }

    public Conment findOneConmentById(int id){
        return conmentMapping.findOneConmentById(id);
    }

    public List<Conment> findConmentIsParentByBlogId(int blogId,int offset){
        return conmentMapping.findConmentsIsParentByBlogId(blogId,offset);
    }

    public List<Conment> findConmentsByConmentParentID(int conmentParentId){
        return conmentMapping.findConmentsByConmentPrentId(conmentParentId);
    }

    public int addConment(String content,int visitor_id,int blog_id,int conment_parent_id){
        return conmentMapping.addConment(content,visitor_id,blog_id,conment_parent_id);
    }

    public synchronized Conment findLastConment(){
        return conmentMapping.findLastConment();
    }

    public int conmnetTotal(int blogId){
        return conmentMapping.conmnetTotal(blogId);
    }

    public int increaseConmentZan(int conmentId){
        return conmentMapping.increaseConmentZan(conmentId);
    }

    public int increaseConmentCai(int conmentId){
        return conmentMapping.increaseConmentCai(conmentId);
    }

    /**
     * 返回评论的总数
     * @return
     */
    public int conmnettotal(){
        return conmentMapping.conmnettotal();
    }

    /**
     * 根据索引返回7条评论
     * @param offset
     * @return
     */
    public List<Conment> findSomeConments(int offset){
        return conmentMapping.findSomeConments(offset);
    }

    /**
     * 通过id删除评论
     * @param id
     * @return
     */
    public int deleteCommentById(int id){
        return conmentMapping.deleteCommentById(id);
    }
}
