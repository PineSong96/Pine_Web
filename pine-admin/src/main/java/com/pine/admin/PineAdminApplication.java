package com.pine.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Administrator
 */
@EnableScheduling   //开启定时器
@SpringBootApplication
@ServletComponentScan
@SpringBootConfiguration
@MapperScan(value = "com.pine.admin.modules.*.dao")
public class PineAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PineAdminApplication.class, args);
    }

}

