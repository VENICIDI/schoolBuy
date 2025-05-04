package com.example.schoolbuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.schoolbuy.mapper")
public class SchoolBuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolBuyApplication.class, args);
    }

}
