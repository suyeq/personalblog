package com.suye.personalblog.service;

import com.suye.personalblog.model.LogMessage;
import com.suye.personalblog.redisrepository.LogRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-07
 * Time: 21:16
 */
@Service
public class LogMessageService {

    @Autowired
    private LogRedisRepository logRedisRepository;

    /**
     * 增加一个系统信息
     * @param action
     */
    public void  addALog(String action){
        logRedisRepository.addLogMessage(action);
    }

    /**
     * 获取所有的系统信息
     * @return
     */
    public List<LogMessage> getLogMessage(){
        List<LogMessage> list=logRedisRepository.getLogMessage();
        List<LogMessage> logMessageList=new ArrayList<>();
        for (int i=list.size()-1;i>list.size()-10;i--){
            logMessageList.add(list.get(i));
        }
        return logMessageList;
    }
}
