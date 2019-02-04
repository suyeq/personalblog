package com.suye.personalblog.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 20:02
 */
//博客
public class Blog implements Serializable,BlogMessageInterface {
    private int id;
    private String title;
    private String imgUrl;
    private String describ;
    private String content;
    private Date create_time;
    private int readnum;
    private int votenum;
    private int cainum;
    private int conmentnum;
    private int isTalk;
    private int isComment;
    private int isPublish;
    private String htmlcontent;
    private int imgid;


    public Blog(){}

    public Blog(int id, String imgUrl, String title, String describ, String content, Date create_time, int readnum, int votenum, int conmentnum, int isTalk,int cainum,int isComment,int isPublish,String htmlcontent,int imgid){
        this.id=id;
        this.title=title;
        this.imgUrl=imgUrl;
        this.describ=describ;
        this.content=content;
        this.create_time=create_time;
        this.readnum=readnum;
        this.votenum=votenum;
        this.conmentnum=conmentnum;
        this.isTalk=isTalk;
        this.cainum=cainum;
        this.isComment=isComment;
        this.isPublish=isPublish;
        this.htmlcontent=htmlcontent;
        this.imgid=imgid;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public int getVotenum() {
        return votenum;
    }

    public void setVotenum(int votenum) {
        this.votenum = votenum;
    }

    public int getConmentnum() {
        return conmentnum;
    }

    public void setConmentnum(int conmentnum) {
        this.conmentnum = conmentnum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public int getIsTalk() {
        return isTalk;
    }

    public void setIsTalk(int isTalk) {
        this.isTalk = isTalk;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCainum() {
        return cainum;
    }

    public void setCainum(int cainum) {
        this.cainum = cainum;
    }

    public int getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(int isPublish) {
        this.isPublish = isPublish;
    }

    public int getIsComment() {
        return isComment;
    }

    public void setIsComment(int isComment) {
        this.isComment = isComment;
    }

    public String getHtmlcontent() {
        return htmlcontent;
    }

    public void setHtmlcontent(String htmlcontent) {
        this.htmlcontent = htmlcontent;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
