package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogLabelMapping;
import com.suye.personalblog.mapping.LabelMapping;
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

    public List<Label> getAllLabels(){
        return labelMapping.getAllLabels();
    }

    public Label findLabelById(int id){
        return labelMapping.findLabelById(id);
    }

    public List<Integer> findLabelsIdByBlogId(int id){
        return blogLabelMapping.findLabesIdByBlogId(id);
    }

    public List<Label> findLabelsByBlogId(int id){
        List<Integer> list=findLabelsIdByBlogId(id);
        List<Label> labelList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            labelList.add(findLabelById(list.get(i)));
        }
        return labelList;
    }

    public List<Integer> findBlogIdsByLabelId(int labelId,int offset){
        return labelMapping.findBlogIdsByLabelId(labelId,offset);
    }

    public Label findLabelByLabelName(String name){
        return labelMapping.findLabelByLabelName(name);
    }

    public int addLabel(String name,String describ){
        return labelMapping.addLabel(name,describ);
    }

    public int lastLabel(){
        return labelMapping.lastLabel();
    }

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
}
