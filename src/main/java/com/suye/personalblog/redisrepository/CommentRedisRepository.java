package com.suye.personalblog.redisrepository;

import com.suye.personalblog.model.Conment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-07
 * Time: 9:46
 */
@Repository
public class CommentRedisRepository {
    private static String HEAD="comment:";
    @Autowired
    RedisTemplate redisTemplate;

    public void addComment(Conment conment,int timeout){
        redisTemplate.opsForValue().set(HEAD+conment.getId(),conment,timeout, TimeUnit.MILLISECONDS);
    }

    public Conment getConment(int conmentId){
        return (Conment) redisTemplate.opsForValue().get(HEAD+conmentId);
    }

    public void delComment(int id){
        redisTemplate.delete(HEAD+id);
    }
}
