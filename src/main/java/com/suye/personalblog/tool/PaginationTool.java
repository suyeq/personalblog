package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public int increaseOffset(){
        return ++offset;
    }

    public  List<Blog> blogList(){
        List<Blog> blogList;
        if (category==Category.ALL){
            blogList=blogService.loadMoreRecentBlogs(offset*7);
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

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    enum Category{
        SEARCH,ALL,BLOG,SHUOSHUO,COLUMN,CATEGORY,LABEL
    }
}
