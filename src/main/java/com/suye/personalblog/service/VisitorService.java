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

    public List<Visitor> getAllVisitor(){
        return visitorMapping.getAllVisitor();
    }

    public Visitor findOneById(int id){
        return visitorMapping.findOneById(id);
    }

    public void addVisitor(String name,String email,String address){
        visitorMapping.addVisitor(name,email,address);
    }

    public boolean isHaveThisVisitor(String email){
        if (visitorMapping.findOneByEmail(email)!=null){
            return true;
        }
        return false;
    }

    public Visitor findOneByEmail(String email){
        return visitorMapping.findOneByEmail(email);
    }

    public List<Visitor> findAllFriends(){
        return visitorMapping.findAllFriends();
    }

}
