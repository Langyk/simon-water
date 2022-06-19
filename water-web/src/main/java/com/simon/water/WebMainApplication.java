package com.simon.water;

import com.simon.water.common.pojo.SmsParam;
import com.simon.water.handler.script.TencentSmsScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

/**
 * creat by 郎亚坤
 * 2022/6/19 20:16
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
public class WebMainApplication {
    private final Logger logger = LoggerFactory.getLogger(WebMainApplication.class);

    @Autowired
    private TencentSmsScript tencentSmsScript;

    public static void main(String[] args) {
        SpringApplication.run(WebMainApplication.class,args);
    }

    @GetMapping("/sendSms")
    public String hello(){

        SmsParam smsParam = SmsParam.builder()
                .phones(new HashSet<>(Arrays.asList("19802110062")))
                .content("我是郎同学爱喝凉白开") //暂不支持文案的功能
                .build();

        return tencentSmsScript.send(smsParam);
    }
}

