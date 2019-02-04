package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-30
 * Time: 12:06
 */
//用于分页
@Component
public class PaginationTool {
    private  Category category;
    private  Integer offset=1;
    private String content;
    private int columnId;
    private int labelId;
    private int categoryId;
    @Autowired
    BlogService blogService;

    public static int pageTotal(int total){
        int pages=0;
        if (total%7==0){
            return pages=total/7;
        }else {
            return pages=total/7+1;
        }
    }

    public static int pageTotal(int total,String message){
        int pages=0;
        if (total%12==0){
            return pages=total/12;
        }else {
            return pages=total/12+1;
        }
    }

    public void setCategorySearch(){
        category=Category.SEARCH;
    }

    public void setCategoryAll(){
        category=Category.ALL;
    }

    public void setCategoryBlog(){
        category=Category.BLOG;
    }

    public void setCategoryShuoShuo(){
        category=Category.SHUOSHUO;
    }

    public void setCategoryCategory(){
        category=Category.CATEGORY;
    }

    public void setCategoryLabel(){
        category=Category.LABEL;
    }

    public void setCategoryColumn(){
        category=Category.COLUMN;
    }

    public void reset(){
        category=null;
        offset=1;
    }

    public void reset(HttpServletRequest request,Category category){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");
        synchronized (this){
            if (pageMessage==null){
                pageMessage=new PageMessage();
            }
        }
        pageMessage.setOffset(1);
        pageMessage.setCategory(category);
        request.getSession().setAttribute("loadmore",pageMessage);
    }

    public int increaseOffset(){
        return ++offset;
    }

    public List<Blog> blogList(HttpServletRequest request){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");

        int offset=0;
        List<Blog> blogList;
        if (pageMessage.getCategory()==Category.ALL){
            blogList=blogService.loadMoreBlogsHadPublish(pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else if (pageMessage.getCategory()==Category.BLOG){
            blogList=blogService.loadMoreRecentNotShuoShuo(pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else if (pageMessage.getCategory()==Category.SHUOSHUO){
            blogList=blogService.loadMoreRecentIsShuoShuo(pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else if (pageMessage.getCategory()==Category.SEARCH){
            blogList=blogService.loadMoreSearch((String) pageMessage.getMessage(),pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else if (pageMessage.getCategory()==Category.COLUMN){
            blogList=blogService.findBlogsByColumnId((Integer) pageMessage.getMessage(),pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else if (pageMessage.getCategory()==Category.LABEL){
            blogList=blogService.findBlogsByLabelId((Integer) pageMessage.getMessage(),pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }else {
            blogList=blogService.findBlogsByCategoryId((Integer) pageMessage.getMessage(),pageMessage.getOffset()*7);
            offset=pageMessage.getOffset();
            offset++;
            pageMessage.setOffset(offset);
            request.getSession().setAttribute("loadmore",pageMessage);
            return blogList;
        }
    }

    public  List<Blog> blogList(){
        List<Blog> blogList;
        if (category==Category.ALL){
            blogList=blogService.loadMoreBlogsHadPublish(offset*7);
            offset++;
            return blogList;
        }else if (category==Category.BLOG){
            blogList=blogService.loadMoreRecentNotShuoShuo(offset*7);
            offset++;
            return blogList;
        }else if (category==Category.SHUOSHUO){
            blogList=blogService.loadMoreRecentIsShuoShuo(offset*7);
            offset++;
            return blogList;
        }else if (category==Category.SEARCH){
            blogList=blogService.loadMoreSearch(this.content,offset*7);
            offset++;
            return blogList;
        }else if (category==Category.COLUMN){
            blogList=blogService.findBlogsByColumnId(this.columnId,offset*7);
            offset++;
            return blogList;
        }else if (category==Category.LABEL){
            blogList=blogService.findBlogsByLabelId(this.labelId,offset*7);
            offset++;
            return blogList;
        }else {
            blogList=blogService.findBlogsByCategoryId(this.categoryId,offset*7);
            offset++;
            return blogList;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(String content,HttpServletRequest request){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");
        pageMessage.setMessage(content);
        request.getSession().setAttribute("loadmore",pageMessage);
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public void setColumnId(Integer columnId,HttpServletRequest request){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");
        pageMessage.setMessage(columnId);
        request.getSession().setAttribute("loadmore",pageMessage);
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public void setLabelId(Integer labelId,HttpServletRequest request){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");
        pageMessage.setMessage(labelId);
        request.getSession().setAttribute("loadmore",pageMessage);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryId(Integer categoryId,HttpServletRequest request){
        PageMessage pageMessage=(PageMessage)request.getSession().getAttribute("loadmore");
        pageMessage.setMessage(categoryId);
        request.getSession().setAttribute("loadmore",pageMessage);
    }

    public enum Category{
        SEARCH,ALL,BLOG,SHUOSHUO,COLUMN,CATEGORY,LABEL
    }

    class PageMessage{
        private Category category;
        private int offset;
        private Object message;

        public PageMessage(){

        }

        public PageMessage(Category category,int offset,Object message){
            this.category=category;
            this.offset=offset;
            this.message=message;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public Object getMessage() {
            return message;
        }

        public void setMessage(Object message) {
            this.message = message;
        }
    }
}
