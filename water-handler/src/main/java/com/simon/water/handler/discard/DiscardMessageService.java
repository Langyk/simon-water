package com.simon.water.handler.discard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.simon.water.common.constant.WaterConstant;
import com.simon.water.common.domain.TaskInfo;
import org.springframework.stereotype.Service;

/**
 * creat by 郎亚坤
 * 2022/7/30 23:38
 */

/**
 * 丢弃模板消息
 */
@Service
public class DiscardMessageService {
    private static final String DISCARD_MESSAGE_KEY = "discard";

    @ApolloConfig("boss.austin")
    private Config config;

    /**
     * 发送的信息的模板不存在，丢弃该消息
     * 丢弃消息，配置apollo
     * @param taskInfo
     * @return
     */
    public boolean isDiscard(TaskInfo taskInfo){
        JSONArray array = JSON.parseArray(config.getProperty(DISCARD_MESSAGE_KEY,
                WaterConstant.APOLLO_DEFAULT_VALUE_JSON_ARRAY));
        if(array.contains(String.valueOf(taskInfo.getMessageTemplateId()))){
            return true;
        }
        return false;
    }

}

