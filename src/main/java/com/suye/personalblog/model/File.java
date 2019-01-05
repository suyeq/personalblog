package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-04
 * Time: 11:49
 */
//用来保存文件的信息
public class File {
    private int id;
    private String name;
    private String url;

    protected File(){}

    public File(int id,String name,String url){
        this.id=id;
        this.name=name;
        this.url=url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
