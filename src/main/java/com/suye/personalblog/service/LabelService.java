package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogLabelMapping;
import com.suye.personalblog.mapping.BlogMapping;
import com.suye.personalblog.mapping.LabelMapping;
import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 22:41
 */
@Service
public class LabelService {
    @Autowired
    private LabelMapping labelMapping;
    @Autowired
    private BlogLabelMapping blogLabelMapping;
    @Autowired
    private BlogMapping blogMapping;

    /**
     * 获取所有的标签
     * @return
     */
    public List<Label> getAllLabels(){
        return labelMapping.getAllLabels();
    }

    /**
     * 通过id查找标签
     * @param id
     * @return
     */
    public Label findLabelById(int id){
        return labelMapping.findLabelById(id);
    }

    /**
     * 通过博客id查找标签id
     * @param id
     * @return
     */
    public List<Integer> findLabelsIdByBlogId(int id){
        return blogLabelMapping.findLabesIdByBlogId(id);
    }

    /**
     * 查找该博客下的所有标签
     * @param id
     * @return
     */
    public List<Label> findLabelsByBlogId(int id){
        List<Integer> list=findLabelsIdByBlogId(id);
        List<Label> labelList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            labelList.add(findLabelById(list.get(i)));
        }
        return labelList;
    }

    /**
     * 根据标签id返回已发布且不为说说的博客
     * @param labelId
     * @param offset
     * @return
     */
    public List<Integer> findBlogIdsByLabelId(int labelId,int offset){
        List<Integer> blogIds=labelMapping.findBlogIdsByLabelId(labelId,offset);
        List<Integer> newBlogIds=new ArrayList<>();
        Blog blog=null;
        for (int i=0;i<blogIds.size();i++){
            blog=blogMapping.findOneBlog(blogIds.get(i));
            if ((blog.getIsPublish()>0)&&(blog.getIsTalk()!=1)){
                newBlogIds.add(blogIds.get(i));
            }
        }
        return newBlogIds;
    }

    /**
     * 通过标签名查找标签
     * @param name
     * @return
     */
    public Label findLabelByLabelName(String name){
        return labelMapping.findLabelByLabelName(name);
    }

    /**
     * 增加一个标签
     * @param name
     * @param describ
     * @return
     */
    public int addLabel(String name,String describ){
        return labelMapping.addLabel(name,describ);
    }

    /**
     * 获取最后一个标签
     * @return
     */
    public int lastLabel(){
        return labelMapping.lastLabel();
    }

    /**
     * 插入该博客相关联的标签
     * @param blogId
     * @param labelList
     * @return
     */
    public int  insertLabeltoBlog(int blogId,List<String> labelList){
        for (int i=0;i<labelList.size();i++){
            int labelId=0;
            Label label=findLabelByLabelName(labelList.get(i));
            if (label==null){
                addLabel(labelList.get(i),null);
                labelId=lastLabel();
            }else {
                labelId=label.getId();
            }
            labelMapping.insertLabeltoBlog(blogId,labelId);
        }
        return 1;
    }

    /**
     * 从blog_label表里删除有关指定id标签的数据
     * @param labelId
     * @return
     */
    public int deleteLabelWithBlogByLabelId(int labelId){
        return labelMapping.deleteLabelWithBlogByLabelId(labelId);
    }

    /**
     * 删除一个标签
     * @param labelId
     * @return
     */
    @Transient
    public int deleteLabelById(int labelId){
        deleteLabelWithBlogByLabelId(labelId);
        return labelMapping.deleteLabelById(labelId);
    }

    /**
     * 自增该标签下的博客数量
     * @param id
     * @return
     */
    public int increaseBlogNum(int id){
        return labelMapping.increaseBlogNum(id);
    }
}
