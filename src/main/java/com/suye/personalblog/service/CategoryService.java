package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogCategoryMapping;
import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.mapping.CategoryMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 22:58
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapping categoryMapping;

    @Autowired
    private BlogCategoryMapping blogCategoryMapping;
    @Autowired
    private BlogMapping blogMapping;

    /**
     * 获取全部的分类
     * @return
     */
    public List<Category> getAllCategory(){
        return categoryMapping.getAllCategory();
    }

    public Category findOneById(int id){
        return categoryMapping.findOneById(id);
    }

    public List<Integer> findCategoryIdsByBlogId(int id){
        return blogCategoryMapping.findCategoryIdsByBlogId(id);
    }

    /**
     * 通过博客id获取该博客的分类
     * @param id
     * @return
     */
    public List<Category> findCategoryByBlogId(int id){
        List<Integer> list=findCategoryIdsByBlogId(id);
        List<Category> categoryList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            categoryList.add(findOneById(list.get(i)));
        }
        return categoryList;
    }

    /**
     * 通过分类id返回已发布且不是说说的博客
     * @param categoryId
     * @param offset
     * @return
     */
    public List<Integer> findBlogIdsByCategoryId(int categoryId,int offset){
        List<Integer> blogIdList=categoryMapping.findBlogIdsByCategoryId(categoryId,offset);
        List<Integer> newBlogIdList=new ArrayList<>();
        for (int i=0;i<blogIdList.size();i++){
            Blog blog=blogMapping.findOneBlog(blogIdList.get(i));
            if ((blog.getIsTalk()!=1)&&(blog.getIsPublish()>0)){
                newBlogIdList.add(blogIdList.get(i));
            }
        }
        return newBlogIdList;
    }

    public Category findCategoryByCategoryName(String name){
        return categoryMapping.findCategoryByCategoryName(name);
    }

    public int addCategory(String name){
        return categoryMapping.addCategory(name);
    }

    public int lastCategory(){
        return categoryMapping.lastCategory();
    }

    /**
     * 插入该博客的分类
     * @param blogId
     * @param categoryList
     * @return
     */
    public int insertCategorytoBlog(int blogId,List<String> categoryList){
        for (int i=0;i<categoryList.size();i++){
            int categoryId=0;
            Category category=findCategoryByCategoryName(categoryList.get(i));
            if (category==null){
                addCategory(categoryList.get(i));
                categoryId=lastCategory();
            }else {
                categoryId=category.getId();
            }
            categoryMapping.insertCategorytoBlog(blogId,categoryId);
        }
        return 1;
    }

    public int deleteCategoryWithBlogBy(int categoryId){
        return categoryMapping.deleteCategoryWithBlogBy(categoryId);
    }

    /**
     * 通过id删除分类
     * @param categoryId
     * @return
     */
    @Transient
    public int deleteCategoryById(int categoryId){
        deleteCategoryWithBlogBy(categoryId);
        return categoryMapping.deleteCategoryById(categoryId);
    }

    /**
     * 自增该类别下的博客
     * @param id
     * @return
     */
    public int increaseBlogNum(int id){
        return categoryMapping.increaseBlogNum(id);
    }
}
