package com.simon.water.kafkaTest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * creat by 郎亚坤
 * 2022/6/26 17:36
 */

@Component
public class UserLogProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendLog(String userid){
        UserLog userLog = new UserLog();
        userLog.setUserName("jhp").setUserid(userid).setState("0");
        System.err.println("发送用户日志数据:"+userLog);
        kafkaTemplate.send("austin", JSON.toJSONString(userLog));
    }
}

