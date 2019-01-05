package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-28
 * Time: 20:15
 */
//归档信息转化
@Component
public class ArchiveMessageConversion {

    public List<ArchiveMessage> archiveMessageList(List<Blog> blogList){
        List<ArchiveMessage> archiveMessageList=new ArrayList<>();
        List<ArchiveChildMessage> archiveChildMessageList=archiveChildMessageList(blogList);

        for (int i=0;i<archiveChildMessageList.size();i++){
            if (!findisContain(archiveMessageList,archiveChildMessageList.get(i).getYear())){
                //创建年list
                //创建月list
                //插入数据，i++
                ArchiveMessage archiveMessageYear=new ArchiveMessage(archiveChildMessageList.get(i).getYear(),
                        null,null);
                List<ArchiveMessage> newListMonth=new ArrayList<>();
                archiveMessageYear.setList(newListMonth);
                archiveMessageList.add(archiveMessageYear);
                //创建月信息
                ArchiveMessage archiveMessageMonth=new ArchiveMessage(archiveChildMessageList.get(i).getMonth(),
                        null,null);
                List<ArchiveMessage> newListDay=new ArrayList<>();
                archiveMessageMonth.setList(newListDay);

                archiveMessageYear.getList().add(archiveMessageMonth);
                //System.out.println("size2"+);
                ArchiveMessage archiveMessageDay=new ArchiveMessage();
                archiveMessageDay.setDateTitle(archiveChildMessageList.get(i).day);
                archiveMessageDay.setTitle(archiveChildMessageList.get(i).getBlogTitle()
                                            +"("+archiveChildMessageList.get(i).getConmentnum()+"条评论)");

                archiveMessageMonth.getList().add(archiveMessageDay);
            }else {
                //判断月列表是否存在，不存在则创建
                //插入数据，i++
                if (!findContain(archiveMessageList,archiveChildMessageList.get(i).getYear(),archiveChildMessageList.get(i).getMonth())){
                    //创建月信息
                    ArchiveMessage archiveMessageMonth=new ArchiveMessage(archiveChildMessageList.get(i).getMonth(),
                                                                    null,null);
                    List<ArchiveMessage> newListDay=new ArrayList<>();
                    archiveMessageMonth.setList(newListDay);

                    ArchiveMessage archiveMessageDay=new ArchiveMessage();
                    archiveMessageDay.setDateTitle(archiveChildMessageList.get(i).getDay());
                    archiveMessageDay.setTitle(archiveChildMessageList.get(i).getBlogTitle()
                            +"("+archiveChildMessageList.get(i).getConmentnum()+"条评论)");

                    List<ArchiveMessage> mothList=findYearList(archiveMessageList,archiveChildMessageList.get(i).getYear());

                    archiveMessageMonth.getList().add(archiveMessageDay);
                    mothList.add(archiveMessageMonth);
                }else {
                    List<ArchiveMessage> listDay=findMonthList(archiveMessageList,archiveChildMessageList.get(i).getYear(),archiveChildMessageList.get(i).getMonth());
                    ArchiveMessage archiveMessageDay=new ArchiveMessage();
                    archiveMessageDay.setDateTitle(archiveChildMessageList.get(i).getDay());
                    archiveMessageDay.setTitle(archiveChildMessageList.get(i).getBlogTitle()
                            +"("+archiveChildMessageList.get(i).getConmentnum()+"条评论)");
                    listDay.add(archiveMessageDay);
                }
            }
        }

        return archiveMessageList;
    }

    private boolean findisContain(List<ArchiveMessage> list,String date){
        for (int i=0;i<list.size();i++){
            if (list.get(i).getDateTitle().equals(date)){
                return true;
            }
        }
        return false;
    }

    private boolean findContain(List<ArchiveMessage> list,String year,String month){
        int yearindex=0;
        for (int i=0;i<list.size();i++){
            if (list.get(i).getDateTitle().equals(year)){
                yearindex=i;
                break;
            }
        }
        List<ArchiveMessage> listMonth=list.get(yearindex).getList();
        for (int i=0;i<listMonth.size();i++){
            if (listMonth.get(i).getDateTitle().equals(month)){
                return true;
            }
        }
        return false;
    }

    private List<ArchiveMessage> findYearList(List<ArchiveMessage> list,String year){
        for (int i=0;i<list.size();i++){
            if (list.get(i).getDateTitle().equals(year)){
                return list.get(i).getList();
            }
        }
        return null;
    }

    private List<ArchiveMessage> findMonthList(List<ArchiveMessage> list,String year,String month){
        int yearindex=0;
        for (int i=0;i<list.size();i++){
            if (list.get(i).getDateTitle().equals(year)){
                yearindex=i;
                break;
            }
        }
        List<ArchiveMessage> listMonth=list.get(yearindex).getList();
        for (int i=0;i<listMonth.size();i++){
            if (listMonth.get(i).getDateTitle().equals(month)){
                return listMonth.get(i).getList();
            }
        }
        return null;
    }

    private List<ArchiveChildMessage> archiveChildMessageList(List<Blog> blogList){
        List<ArchiveChildMessage> archiveChildMessage=new ArrayList<>();
        for (int i=0;i<blogList.size();i++){
            Date date=new Date(blogList.get(i).getCreate_time().getTime()-8*60*60*1000);
            //System.out.println(date);
            archiveChildMessage.add(new ArchiveChildMessage(date.toString().substring(24),
                                                            TimeConversion.monthConversion(date.toString().substring(4,7)),
                                                            date.toString().substring(8,10),
                                                            blogList.get(i).getTitle(),blogList.get(i).getConmentnum()));
        }
        return archiveChildMessage;
    }


    public static class ArchiveMessage{
        private String dateTitle;
        private String title;
        private List<ArchiveMessage> list;

        public ArchiveMessage(){}
        public ArchiveMessage(String dateTitle,String title,List<ArchiveMessage> list){
            this.dateTitle=dateTitle;
            this.title=title;
            this.list=list;
        }

        public String getDateTitle() {
            return dateTitle;
        }

        public void setDateTitle(String dateTitle) {
            this.dateTitle = dateTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ArchiveMessage> getList() {
            return list;
        }

        public void setList(List<ArchiveMessage> list) {
            this.list = list;
        }


    }

    static class ArchiveChildMessage{
        private String year;
        private String month;
        private String day;
        private String blogTitle;
        private int conmentnum;

        public ArchiveChildMessage(String year,String month,String day,String blogTitle,int conmentnum){
            this.year=year;
            this.month=month;
            this.day=day;
            this.blogTitle=blogTitle;
            this.conmentnum=conmentnum;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getBlogTitle() {
            return blogTitle;
        }

        public void setBlogTitle(String blogTitle) {
            this.blogTitle = blogTitle;
        }

        public int getConmentnum() {
            return conmentnum;
        }

        public void setConmentnum(int conmentnum) {
            this.conmentnum = conmentnum;
        }

        public String toString(){
            return "[title"+blogTitle+"]";
        }
    }
}
