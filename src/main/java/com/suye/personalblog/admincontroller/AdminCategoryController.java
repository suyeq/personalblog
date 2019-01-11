package com.suye.personalblog.admincontroller;

import com.suye.personalblog.StaticField;
import com.suye.personalblog.model.Category;
import com.suye.personalblog.service.CategoryService;
import com.suye.personalblog.service.LabelService;
import com.suye.personalblog.service.LogMessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-03
 * Time: 9:39
 */
@Controller
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping("/admin/category/save")
    @ResponseBody
    public String addCategory(@RequestParam("cname")String name){
        System.out.println(name);
        Category category=categoryService.findCategoryByCategoryName(name);
        if (category!=null){
            return "{\n" + "  \"msg\":\"分类已存在\"\n" + "}";
        }else {
            categoryService.addCategory(name);
            logMessageService.addALog(StaticField.ADD_CATEGORY);
            return "{\n" + "  \"success\":\"保存成功\"\n" + "}";
        }
    }

    @RequestMapping("/admin/category/delete")
    @ResponseBody
    public String deleteCategory(@RequestParam("mid")int id,@RequestParam("kmethor")String method){
        if (method.equals("分类")){
            if (categoryService.deleteCategoryById(id)>0){
                logMessageService.addALog(StaticField.DELETE_CATEGORY);
                return "{\n" + "  \"success\":\"分类删除成功\"\n" + "}";
            }
            return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
        }else {
            if (labelService.deleteLabelById(id)>0){
                logMessageService.addALog(StaticField.DELETE_LABEL);
                return "{\n" + "  \"success\":\"标签删除成功\"\n" + "}";
            }
            return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
        }
    }


}
