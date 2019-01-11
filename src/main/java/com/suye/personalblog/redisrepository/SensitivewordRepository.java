package com.suye.personalblog.redisrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-08
 * Time: 21:36
 */
@Repository
public class SensitivewordRepository {
    private static String HEAD="SensitiveWord";

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean  addASensitiveWord(String word){
        List<String> wordList=getSensitiveKeyWords();
        if (wordList==null){
            wordList=new ArrayList<>();
        }
        if (wordList.contains(word)){
            return false;
        }
        wordList.add(word);
        redisTemplate.opsForValue().set(HEAD,wordList);
        return true;
    }

    public List<String> getSensitiveKeyWords(){
        return (List<String>) redisTemplate.opsForValue().get(HEAD);
    }

    public void deleteASensitiveWord(String word){
        List<String> wordList=getSensitiveKeyWords();
        if (wordList==null){
            wordList=new ArrayList<>();
        }
        wordList.remove(word);
        redisTemplate.opsForValue().set(HEAD,wordList);
    }

//    public void modifySensitiveWord(String word){
//        List<String> wordList=getSensitiveKeyWords();
//        if (wordList==null){
//            wordList=new ArrayList<>();
//        }
//
//    }
}
