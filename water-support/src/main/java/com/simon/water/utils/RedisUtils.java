package com.simon.water.utils;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/7/19 21:04
 */

@Component
@Slf4j
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * mGet将结果封装为Map
     * @param keys
     * @return
     */
    public Map<String,String> mGet(List<String> keys){
        HashMap<String,String> result = new HashMap<>(keys.size());
        try {
            List<String> value = redisTemplate.opsForValue().multiGet(keys);
            if(CollUtil.isNotEmpty(value)){
                for(int i = 0; i < keys.size(); i++){
                        result.put(keys.get(i),value.get(i));
                }
            }

        }catch (Exception e){
            log.error("redis mGet fail! {}",e);

        }
        return result;

    }


    /**
     * 使用pipeline的形式批量处理，可以减少redis服务端对系统的开销
     * pipeline 设置key-value并设置过期时间
     * @param keyValues
     * @param seconds
     */
    public void pipelineSexEX(Map<String,String> keyValues, Long seconds){
        try {
            redisTemplate.executePipelined((RedisCallback<String>) connection->{
                for (Map.Entry<String,String> entry : keyValues.entrySet()){
                    connection.setEx(entry.getKey().getBytes(),seconds,
                            entry.getValue().getBytes());
                }
                return null;
            });
        }catch (Exception e){
            log.error("redis pipelineSetEx fail! e:{}", Throwables.getStackTraceAsString(e));
        }
    }

}

