package com.example.yiya_backend_1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.yiya_backend_1.mapper")
public class YiyaBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(YiyaBackend1Application.class, args);
    }

}
