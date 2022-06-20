package com.simon.water;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
 * creat by 郎亚坤
 * 2022/6/19 20:16
 */
@SpringBootApplication()
@RestController
public class WebMainApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebMainApplication.class,args);
    }

}

