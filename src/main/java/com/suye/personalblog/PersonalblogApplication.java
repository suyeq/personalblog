package com.suye.personalblog;

import com.suye.personalblog.FileServer.work.FileUploadServer;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.suye.personalblog.mapping")
public class PersonalblogApplication {

    //private static final int FILE_PORT = 9991;

    public static void main(String[] args) {
        //启动netty图片服务器
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    new FileUploadServer().bind(FILE_PORT);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        SpringApplication.run(PersonalblogApplication.class, args);
    }

        /**
          * 文件上传大小配置
          * @return
          */
        @Bean
        public MultipartConfigElement multipartConfigElement() {
             MultipartConfigFactory factory = new MultipartConfigFactory();
            //文件最大
            factory.setMaxFileSize("2048MB"); //KB,MB
            /// 设置总上传数据总大小
            factory.setMaxRequestSize("2048MB");
            return factory.createMultipartConfig();
        }

}

