package com.suye.personalblog.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 20:56
 */
//评论
public class Conment {
    private int id;
    private String content;
    private Date conment_time;
    private int visitor_id;
    private int blog_id;

    public Conment(){}
    public Conment(int id,String content,Date conment_time,int visitor_id,int blog_id){
        this.id=id;
        this.content=content;
        this.conment_time=conment_time;
        this.visitor_id=visitor_id;
        this.blog_id=blog_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getConment_time() {
        return conment_time;
    }

    public void setConment_time(Date conment_time) {
        this.conment_time = conment_time;
    }

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }
}
