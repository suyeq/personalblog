package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-22
 * Time: 16:39
 */
public class BlogCategory {
    private int id;
    private int blog_id;
    private int category_id;

    protected BlogCategory(){}

    public BlogCategory(int id,int blog_id,int category_id){
        this.id=id;
        this.blog_id=blog_id;
        this.category_id=category_id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
