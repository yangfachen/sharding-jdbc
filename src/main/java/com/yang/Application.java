package com.yang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Classname Application
 * @Description TODO
 * @Date 2022/1/11 15:19
 * @Created by yangchen
 */
@MapperScan("com.yang.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);

    }
}
