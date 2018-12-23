package com.suye.personalblog.service;

import com.suye.personalblog.mapping.BlogLabelMapping;
import com.suye.personalblog.mapping.LabelMapping;
import com.suye.personalblog.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
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
}
