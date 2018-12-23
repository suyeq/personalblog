package com.suye.personalblog.model;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 20:51
 */
//标签
public class Label {
    private int id;
    private String name;
    private String describ;

    public Label(){}
    public Label(int id,String name,String describ){
        this.id=id;
        this.name=name;
        this.describ=describ;
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
}
