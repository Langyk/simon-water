package com.simon.water.controller;

import com.simon.water.kafkaTest.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * creat by 郎亚坤
 * 2022/6/26 17:23
 */

@RestController
public class KafkaTestController {

    @Autowired
    private UserLogProducer userLogProducer;


    /**
     * test insert
     */
    @GetMapping("/kafka/insert")
    public String insert(String userId){
        userLogProducer.sendLog(userId);
        return null;
    }

}

