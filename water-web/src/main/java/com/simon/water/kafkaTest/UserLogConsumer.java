package com.simon.water.kafkaTest;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * creat by 郎亚坤
 * 2022/6/26 17:31
 */

@Component
@Slf4j
public class UserLogConsumer {


//    @KafkaListener(topics = {"austin"},groupId = "simon-test")
//    public void consumer(ConsumerRecord<?,?> consumerRecord){
//        // 判断是否为null
//        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
//        log.info(">>>>>>>>>> record =" + kafkaMessage);
//        if(kafkaMessage.isPresent()){
//            //得到Optional实例中的值
//            Object message = kafkaMessage.get();
//            System.err.println("消费消息:"+message);
//        }
//    }
}

