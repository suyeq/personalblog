package com.suye.personalblog.redisrepository;

import com.suye.personalblog.model.LogMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-07
 * Time: 20:44
 */
//日志仓库
@Repository
public class LogRedisRepository {
    private static String HEAD="logSystem";
    @Autowired
    RedisTemplate redisTemplate;
    private List<LogMessage> logMessageList=null;

    public void addLogMessage(String action){
        logMessageList=getLogMessage();
        if (logMessageList==null){
            logMessageList=new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
        String dateString = sdf.format(new Date());
        logMessageList.add(new LogMessage(dateString,action));
        redisTemplate.opsForValue().set(HEAD,logMessageList);
    }

    public List<LogMessage> getLogMessage(){
        return (List<LogMessage>) redisTemplate.opsForValue().get(HEAD);
    }
}
