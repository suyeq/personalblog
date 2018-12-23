package com.suye.personalblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suye.personalblog.mapping")
public class PersonalblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalblogApplication.class, args);
    }

}

