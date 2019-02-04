package com.suye.personalblog;


import com.suye.personalblog.model.Blog;
import com.suye.personalblog.service.BlogService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


//@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonalblogApplication.class)
@WebAppConfiguration
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonalblogApplicationTests {

    @Autowired
    BlogService blogService;
    @Test
    public void contextLoads() {
        
    }

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
        Blog blog=new Blog();
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }


}

