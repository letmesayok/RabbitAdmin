package com.rabbit;

import cn.dev33.satoken.spring.SaTokenSetup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangql
 * 项目启动类
 */
@MapperScan("com.rabbit.project.mapper")
@SaTokenSetup
@SpringBootApplication
public class RabbitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }
}
