package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.service.CategoryService;
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
public class BlogMessageConversion {

    @Autowired
    LabelService labelService;
    @Autowired
    CategoryService categoryService;

    public  List<BlogMessage> getBlogMessageList(List<Blog> list){
        List<BlogMessage> messages=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Blog blog=list.get(i);
            List<Label> labelList=labelService.findLabelsByBlogId(blog.getId());
            List<Category> categoryList=categoryService.findCategoryByBlogId(blog.getId());
            BlogMessage blogMessage=new BlogMessage(blog.getId(),
                    blog.getTitle(),blog.getImgUrl(),
                    readnumConversion(blog.getReadnum()),
                    TimeConversion.timeConversion(blog.getCreate_time()),
                    conmentnumConversion(blog.getConmentnum()),
                    blog.getVotenum(), blog.getContent(),
                    blog.getIsTalk(),blog.getDescrib(),
                    labelList,categoryList);
            messages.add(blogMessage);
        }
        return messages;
    }

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

         protected BlogMessage(){}

         public BlogMessage(int id,String title,String imgUrl,String readnum,
                            String time,String conmentnum,int votenum,
                            String content,int isTalk,String describ,
                            List<Label> labelList,List<Category> categoryList){
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
     }
}
