package com.suye.personalblog.service;

import com.suye.personalblog.mapping.VisitorMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 19:42
 */
@Service
public class VisitorService {
    @Autowired
    private VisitorMapping visitorMapping;

    /**
     * 获取所有的游客
     * @return
     */
    public List<Visitor> getAllVisitor(){
        return visitorMapping.getAllVisitor();
    }

    /**
     * 通过id找寻一个游客
     * @param id
     * @return
     */
    public Visitor findOneById(int id){
        return visitorMapping.findOneById(id);
    }

    /**
     * 增加一个游客
     * @param name
     * @param email
     * @param address
     */
    public void addVisitor(String name,String email,String address){
        visitorMapping.addVisitor(name,email,address);
    }

    /**
     * 是否包=已有该游客
     * @param email
     * @return
     */
    public boolean isHaveThisVisitor(String email){
        if (visitorMapping.findOneByEmail(email)!=null){
            return true;
        }
        return false;
    }

    /**
     * 通过email找寻该博客
     * @param email
     * @return
     */
    public Visitor findOneByEmail(String email){
        return visitorMapping.findOneByEmail(email);
    }

    /**
     * 从游客中找出所有的友链
     * @return
     */
    public List<Visitor> findAllFriends(){
        return visitorMapping.findAllFriends();
    }

}
