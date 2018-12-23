package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 20:42
 */
//专栏
public class Column {
    private int id;
    private String name;
    private String describ;
    private int blognum;
    private String imgUrl;

    public Column(){}
    public Column(int id,String name,String describ,int blognum,String imgUrl){
        this.id=id;
        this.name=name;
        this.describ=describ;
        this.blognum=blognum;
        this.imgUrl=imgUrl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public int getBlognum() {
        return blognum;
    }

    public void setBlognum(int blognum) {
        this.blognum = blognum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
