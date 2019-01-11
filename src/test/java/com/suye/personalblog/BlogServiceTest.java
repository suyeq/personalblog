package com.suye.personalblog;

import com.suye.personalblog.mapping.BlogMapping;

import com.suye.personalblog.redisrepository.BlogRedisRepository;
import com.suye.personalblog.redisrepository.CommentRedisRepository;
import com.suye.personalblog.redisrepository.LogRedisRepository;

import com.suye.personalblog.service.BlogService;

import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.SensitiveService;
import com.suye.personalblog.tool.ClassicAuotation;
import com.suye.personalblog.tool.SensitiveWordInit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-06
 * Time: 21:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersonalblogApplication.class)
public class BlogServiceTest {
    @Autowired
    BlogService blogService;
    BlogRedisRepository blogRedisRepository;

    @Autowired
    BlogMapping blogMapping;
    @Autowired
    ConmentService conmentService;
    @Autowired
    LogRedisRepository logRedisRepository;
    @Autowired
    CommentRedisRepository commentRedisRepository;
    @Autowired
    SensitiveService sensitiveService;
    @Autowired
    SensitiveWordInit sensitiveWordInit;
    @Test
    public void test() throws IOException {
        //Date date=new Date();
//
////
        String string = "傻**逼**啦啦&&啦#傻子#啦啦啦大@  傻#子";
        //String string="[一两清风，半盏明月]啦啦啦啦啦啦大@傻#子";

                List<String> list=sensitiveService.findSensitiveWords();
        System.out.println(list);
       // SensitiveWordInit sensitiveWordInit=new SensitiveWordInit("[一两清风，半盏明月]");
       sensitiveWordInit.init();
        Set<String> set=sensitiveWordInit.getSensitiveWord(string);
        System.out.println("语句中包含的敏感词汇："+set.size()+"。包含"+set);
        //System.out.println(sensitiveWordInit.keyWordMap.size());
        System.out.println(sensitiveWordInit.replaceSensitiveWord(string,"[一两清风，半盏明月]"));

//        String text="刺客阿啥都傻**逼继强爱是的班夏晨阳陈亚刘继李";
//        System.out.println(text.replaceAll("傻**逼","[一两清风，半盏明月]"));
//        sensitiveService.addSensitiveWord("大傻逼");
//        sensitiveService.addSensitiveWord("傻子");
//        sensitiveService.addSensitiveWord("傻逼");
//        List<String> list=sensitiveService.findSensitiveWords();
//        System.out.println(list);
//        Random random=new Random();
//        random.nextInt(3);
//        int i=10;
//        while ((i--)>0){
//            int k=random.nextInt(4);
//            System.out.println(k);
//        }
//        System.out.println(ClassicAuotation.getAAuotation());
//        System.out.println(ClassicAuotation.getANum());
    }
}
