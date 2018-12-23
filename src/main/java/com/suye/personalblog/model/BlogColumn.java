package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-22
 * Time: 16:53
 */
public class BlogColumn {

    private int id;
    private int blog_id;
    private int column_id;

    protected BlogColumn(){}

    public BlogColumn(int id,int blog_id,int column_id){
        this.id=id;
        this.blog_id=blog_id;
        this.column_id=column_id;
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

    public int getColumn_id() {
        return column_id;
    }

    public void setColumn_id(int column_id) {
        this.column_id = column_id;
    }
}
