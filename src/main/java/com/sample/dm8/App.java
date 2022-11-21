package com.sample.dm8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动应用
 *
 * @author Aaric, created on 2022-11-21T11:39.
 * @version 0.2.0-SNAPSHOT
 */
@MapperScan("com.sample.dm8.dao.*Mapper")
@SpringBootApplication
public class App {

    /**
     * 主函数
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
