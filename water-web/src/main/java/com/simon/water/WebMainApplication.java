package com.simon.water;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


/**
 * creat by 郎亚坤
 * 2022/6/19 20:16
 */
@SpringBootApplication
@EnableApolloConfig
public class WebMainApplication {
    public static void main(String[] args) {
//        System.setProperty("apollo.config-service","http://127.0.0.0:8070");
        System.setProperty("apollo.config-service", "http://localhost:8080");
        SpringApplication.run(WebMainApplication.class,args);

    }

}

