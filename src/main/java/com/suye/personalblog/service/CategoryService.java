package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogCategoryMapping;
import com.suye.personalblog.mapping.CategoryMapping;
import com.suye.personalblog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Category> findCategoryByBlogId(int id){
        List<Integer> list=findCategoryIdsByBlogId(id);
        List<Category> categoryList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            categoryList.add(findOneById(list.get(i)));
        }
        return categoryList;
    }

    public List<Integer> findBlogIdsByCategoryId(int categoryId,int offset){
        return categoryMapping.findBlogIdsByCategoryId(categoryId,offset);
    }
}
