package com.suye.personalblog.service;

import com.suye.personalblog.mapping.ConmentMapping;
import com.suye.personalblog.model.Conment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-21
 * Time: 22:02
 */
@Service
public class ConmentService {

    @Autowired
    private ConmentMapping conmentMapping;

    public List<Conment> recentConment(){
        return conmentMapping.recentConment();
    }


}
