package com.suye.personalblog;

import com.suye.personalblog.mapping.BlogMapping;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.redisrepository.BlogRedisRepository;
import com.suye.personalblog.redisrepository.CommentRedisRepository;
import com.suye.personalblog.redisrepository.LogRedisRepository;

import com.suye.personalblog.service.BlogService;

import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.FileService;
import com.suye.personalblog.service.SensitiveService;
import com.suye.personalblog.tool.BeanFactoryTest;
import com.suye.personalblog.tool.ClassicAuotation;
import com.suye.personalblog.tool.SensitiveWordInit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

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
    //BlogRedisRepository blogRedisRepository;

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

    @Autowired
    BeanFactoryTest beanFactoryTest;
    @Autowired
    FileService fileService;

    @Autowired
    Environment environment;
    @Value("${net.address}")
    private String ip;
    @Value("${net.port}")
    private String port;

    @Test
    public void test() throws IOException {
        //Date date=new Date();
//
////
//        String string = "傻**逼**啦啦&&啦#傻子#啦啦啦大@  傻#子";
//        //String string="[一两清风，半盏明月]啦啦啦啦啦啦大@傻#子";
//
//        List<String> list=sensitiveService.findSensitiveWords();
//        System.out.println(list);
//       // SensitiveWordInit sensitiveWordInit=new SensitiveWordInit("[一两清风，半盏明月]");
//        sensitiveWordInit.init();
//        Set<String> set=sensitiveWordInit.getSensitiveWord(string);
//        System.out.println("语句中包含的敏感词汇："+set.size()+"。包含"+set);
//        //System.out.println(sensitiveWordInit.keyWordMap.size());
//        System.out.println(sensitiveWordInit.replaceSensitiveWord(string,"[一两清风，半盏明月]"));

//        BlogService blogService=beanFactoryTest.getApplicationContext().getBean(BlogService.class);
//        if (blogService==null){
//            System.out.println("adas");
//        }
//        System.out.println(blogService.blogTotal());

            //ThreadLocal<String> threadLocal=new ThreadLocal<>();

//            Set<String> set=new LinkedHashSet<>();
//            set.add("1");
//            set.add("fs");
//            set.add("adsdas00");
//            System.out.println(set);
        //List<Blog> list=blogService.recentBlogs();
        //System.out.println(list.size());
//        Stack<String> stack=new Stack<>();
//        stack.
            //fileService.findOneById(0);
//        InetAddress localHost = null;
//        try {
//            localHost = Inet4Address.getLocalHost();
//        } catch (UnknownHostException e) {
//
//        }
//        String ip = localHost.getHostAddress();
        System.out.println(ip+"  "+port);
    }
}
