package com.simon.water.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * creat by 郎亚坤
 * 2022/7/31 11:01
 */

@RestController
public class ApolloController {

    @ApolloConfig("boss.austin")
    public Config config;

    @RequestMapping("/apollo")
    public String testApollo(){
        System.out.println("测试Apollo");
        return config.getProperty("a","b");
    }
}

