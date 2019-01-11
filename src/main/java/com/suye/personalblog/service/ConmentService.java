package com.suye.personalblog.service;

import com.suye.personalblog.mapping.ConmentMapping;
import com.suye.personalblog.mapping.VisitorMapping;
import com.suye.personalblog.model.Conment;
import com.suye.personalblog.redisrepository.CommentRedisRepository;
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

    private static int TIME_UNIT=1000;
    @Autowired
    private ConmentMapping conmentMapping;
    @Autowired
    private VisitorMapping visitorMapping;
    @Autowired
    private CommentRedisRepository commentRedisRepository;

    /**
     * 最近的评论
     * @return
     */
    public List<Conment> recentConment(){
        return conmentMapping.recentConment();
    }

    /**
     * 通过博客id查找是父评论的评论
     * @param blogId
     * @param offset
     * @return
     */
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


    public String findEmailByCommentId(int id){
        int visitorId=conmentMapping.findOneConmentById(id).getVisitor_id();
        return visitorMapping.findOneById(visitorId).getEmail();
    }

    /**
     * 带缓存的获取
     * @param id
     * @return
     */
    public Conment findOneConmentById(int id){
        Conment  conment= commentRedisRepository.getConment(id);
        if (conment!=null){
            return conment;
        }
        return conmentMapping.findOneConmentById(id);
    }

    /**
     * 带缓存的保存评论
     * @param conment
     * @return
     */
    public Conment saveConment(Conment conment){
        if (conment==null)
            return null;
        commentRedisRepository.addComment(conment,TIME_UNIT);
        conmentMapping.saveConment(conment);
        return findOneConmentById(conment.getId());
    }

    /**
     * 通过id删除评论,带缓存
     * @param id
     * @return
     */
    public int deleteCommentById(int id){
        commentRedisRepository.delComment(id);
        return conmentMapping.deleteCommentById(id);
    }


}
