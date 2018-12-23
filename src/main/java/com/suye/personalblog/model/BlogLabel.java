package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-22
 * Time: 16:43
 */
public class BlogLabel {
    private int id;
    private int blog_id;
    private int label_id;

    protected BlogLabel(){}

    public BlogLabel(int id,int blog_id,int label_id){
        this.id=id;
        this.blog_id=blog_id;
        this.label_id=label_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getLabel_id() {
        return label_id;
    }

    public void setLabel_id(int label_id) {
        this.label_id = label_id;
    }
}
