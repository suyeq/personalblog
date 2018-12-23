package com.suye.personalblog.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 21:00
 */
//恢复评论
public class ReConment {
    private int id;
    private String content;
    private Date conment_time;
    private int conment_id;
    private int visitor_id;

    public ReConment(){}
    public ReConment(int id,String content,Date conment_time,int conment_id,int visitor_id){
        this.id=id;
        this.content=content;
        this.conment_time=conment_time;
        this.conment_id=conment_id;
        this.visitor_id=visitor_id;
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

    public int getConment_id() {
        return conment_id;
    }

    public void setConment_id(int conment_id) {
        this.conment_id = conment_id;
    }

    public int getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(int visitor_id) {
        this.visitor_id = visitor_id;
    }
}
