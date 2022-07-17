package com.simon.water.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * creat by 郎亚坤
 * 2022/7/17 9:32
 */


@RestController
@Slf4j
public class RedisController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/redis")
    public void testRedis(){
        log.info(redisTemplate.opsForValue().get(1));
    }
}

