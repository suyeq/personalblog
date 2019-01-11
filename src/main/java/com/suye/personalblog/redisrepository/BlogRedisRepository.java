package com.suye.personalblog.redisrepository;

import com.suye.personalblog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-06
 * Time: 18:58
 */
@Repository
public class BlogRedisRepository {

    private static String HEAD="blog:";
    @Autowired
    private RedisTemplate redisTemplate;

    public void addBlog(Blog blog,int timeout){
        redisTemplate.opsForValue().set(HEAD+blog.getId(),blog,timeout, TimeUnit.MILLISECONDS);
    }

    public Blog getBlog(int id){
        return (Blog) redisTemplate.opsForValue().get(HEAD+id);
    }

    public void  delBlog(int id){
        redisTemplate.delete(HEAD+id);
    }

}
