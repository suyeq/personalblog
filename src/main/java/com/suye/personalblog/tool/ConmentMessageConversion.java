package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Conment;
import com.suye.personalblog.model.Visitor;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 22:12
 */
@Component
public class ConmentMessageConversion {

    @Autowired
    BlogService blogService;
    @Autowired
    VisitorService visitorService;

    public  List<ConmentMessage> conmentMessageList(List<Conment> list){
        List<ConmentMessage> conmentMessages=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Conment conment=list.get(i);
            String visitorName=visitorService.findOneById(conment.getVisitor_id()).getName();
            String blogName=blogService.findOneById(conment.getBlog_id()).getTitle();
            ConmentMessage conmentMessage=new ConmentMessage(conment.getId(),
                                                            conment.getContent(),
                                                            TimeConversion.timeConversion(conment.getConment_time()),
                                                            visitorName,blogName);
            conmentMessages.add(conmentMessage);
        }
        return conmentMessages;
    }

    public static class ConmentMessage{
        private int id;
        private String content;
        private String time;
        private String visitorName;
        private String blogName;

        public ConmentMessage(int id,String content,String time,String visitorName,String blogName){
            this.id=id;
            this.content=content;
            this.time=time;
            this.visitorName=visitorName;
            this.blogName=blogName;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVisitorName() {
            return visitorName;
        }

        public void setVisitorName(String visitorName) {
            this.visitorName = visitorName;
        }

        public String getBlogName() {
            return blogName;
        }

        public void setBlogName(String blogName) {
            this.blogName = blogName;
        }
    }
}
