package com.suye.personalblog.tool;

import com.suye.personalblog.model.*;
import com.suye.personalblog.service.CategoryService;
import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.FileService;
import com.suye.personalblog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 16:30
 */
@Component
public class BlogMessageConversion implements BlogMessageInterface {

    @Autowired
    LabelService labelService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ConmentService conmentService;
    @Autowired
    FileService fileService;

    /**
     * 获取所有的博客信息
     * @param list
     * @return
     */
    public  List<BlogMessage> getBlogMessageList(List<Blog> list){
        List<BlogMessage> messages=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Blog blog=list.get(i);
            int comments=conmentService.conmnetTotal(blog.getId());
            List<Label> labelList=labelService.findLabelsByBlogId(blog.getId());
            List<Category> categoryList=categoryService.findCategoryByBlogId(blog.getId());
            Date date=new Date(blog.getCreate_time().getTime()-8*60*60*1000);
            File file=fileService.findOneById(blog.getImgid());
            String imgUrl=null;
            if (file!=null){
                imgUrl=file.getUrl();
            }
           // String imgUrl=fileService.findOneById(blog.getImgid()).getUrl();
            BlogMessage blogMessage=new BlogMessage(blog.getId(),
                    blog.getTitle(),imgUrl,
                    readnumConversion(blog.getReadnum()),
                    TimeConversion.timeConversion(blog.getCreate_time()),
                    conmentnumConversion(blog.getConmentnum()),
                    blog.getVotenum(), blog.getContent(),
                    blog.getIsTalk(),blog.getDescrib(),
                    labelList,categoryList,comments,date,
                    blog.getIsComment(),blog.getIsPublish(),blog.getHtmlcontent());
            messages.add(blogMessage);
        }
        return messages;
    }

    /**
     * 获取单个博客信息
     * @param blog
     * @return
     */
    public BlogMessage getOneBlogMessage(Blog blog){
        List<Label> labelList=labelService.findLabelsByBlogId(blog.getId());
        int comments=conmentService.conmnetTotal(blog.getId());
        List<Category> categoryList=categoryService.findCategoryByBlogId(blog.getId());

        File file=fileService.findOneById(blog.getImgid());
        String imgUrl=null;
        if (file!=null){
            imgUrl=file.getUrl();
        }
        //String imgUrl=fileService.findOneById(blog.getImgid()).getUrl();

        BlogMessage blogMessage=new BlogMessage(blog.getId(),
                blog.getTitle(),imgUrl,
                readnumConversion(blog.getReadnum()),
                TimeConversion.timeConversion(blog.getCreate_time()),
                conmentnumConversion(blog.getConmentnum()),
                blog.getVotenum(), blog.getContent(),
                blog.getIsTalk(),blog.getDescrib(),
                labelList,categoryList,comments,blog.getCreate_time(),
                blog.getIsComment(),blog.getIsPublish(),blog.getHtmlcontent());
        return blogMessage;
    }

    /**
     * 阅读数转化
     * @param readnum
     * @return
     */
    public static String readnumConversion(int readnum){
        if (readnum<0){
            return "0";
        }else if (readnum<1000){
            return readnum+"";
        }else if (readnum<100000){
            DecimalFormat df = new DecimalFormat("0.00");
            float reads=((float)readnum)/1000;
            return df.format(reads)+"K";
        }
        return null;
    }



    public static String conmentnumConversion(int conmentnum){
        return conmentnum+"";
    }


     public static class BlogMessage{
         private int id;
         private String title;
         private String imgUrl;
         private String readnum;
         private String time;
         private String conmentnum;
         private int votenum;
         private String content;
         private int isTalk;
         private String describ;
         private List<Label> labelList;
         private List<Category> categoryList;
         private int comments;
         private Date date;
         private int iscomment;
         private int ispublish;
         private String htmlcontent;

         protected BlogMessage(){}

         public BlogMessage(int id,String title,String imgUrl,String readnum,
                            String time,String conmentnum,int votenum,
                            String content,int isTalk,String describ,
                            List<Label> labelList,List<Category> categoryList,
                            int comments,Date date,int iscomment,int ispublish,String htmlcontent){
            this.id=id;
            this.time=time;
            this.title=title;
            this.imgUrl=imgUrl;
            this.conmentnum=conmentnum;
            this.readnum=readnum;
            this.votenum=votenum;
            this.content=content;
            this.isTalk=isTalk;
            this.describ=describ;
            this.labelList=labelList;
            this.categoryList=categoryList;
            this.comments=comments;
            this.date=date;
            this.iscomment=iscomment;
            this.ispublish=ispublish;
            this.htmlcontent=htmlcontent;
         }

         public int getId() {
             return id;
         }

         public void setId(int id) {
             this.id = id;
         }

         public String getTitle() {
             return title;
         }

         public void setTitle(String title) {
             this.title = title;
         }

         public String getReadnum() {
             return readnum;
         }

         public void setReadnum(String readnum) {
             this.readnum = readnum;
         }

         public String getTime() {
             return time;
         }

         public void setTime(String time) {
             this.time = time;
         }

         public String getConmentnum() {
             return conmentnum;
         }

         public void setConmentnum(String conmentnum) {
             this.conmentnum = conmentnum;
         }

         public String getImgUrl() {
             return imgUrl;
         }

         public void setImgUrl(String imgUrl) {
             this.imgUrl = imgUrl;
         }

         public int getVotenum() {
             return votenum;
         }

         public void setVotenum(int votenum) {
             this.votenum = votenum;
         }

         public String getContent() {
             return content;
         }

         public void setContent(String content) {
             this.content = content;
         }

         public int getIsTalk() {
             return isTalk;
         }

         public void setIsTalk(int isTalk) {
             this.isTalk = isTalk;
         }

         public String getDescrib() {
             return describ;
         }

         public void setDescrib(String describ) {
             this.describ = describ;
         }

         public List<Label> getLabelList() {
             return labelList;
         }

         public void setLabelList(List<Label> labelList) {
             this.labelList = labelList;
         }

         public List<Category> getCategoryList() {
             return categoryList;
         }

         public void setCategoryList(List<Category> categoryList) {
             this.categoryList = categoryList;
         }

         public int getComments() {
             return comments;
         }

         public void setComments(int comments) {
             this.comments = comments;
         }

         public Date getDate() {
             return date;
         }

         public void setDate(Date date) {
             this.date = date;
         }

         public int getIspublish() {
             return ispublish;
         }

         public void setIspublish(int ispublish) {
             this.ispublish = ispublish;
         }

         public int getIscomment() {
             return iscomment;
         }

         public void setIscomment(int iscomment) {
             this.iscomment = iscomment;
         }

         public String getHtmlcontent() {
             return htmlcontent;
         }

         public void setHtmlcontent(String htmlcontent) {
             this.htmlcontent = htmlcontent;
         }
     }
}
