package com.simon.water.handler.script;

import com.simon.water.handler.domain.SmsParam;
import com.simon.water.domain.SmsRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/6/20 20:13
 */

/**
 * 短信脚本，接口
 */
@Component
public interface SmsScript {


    /**
     * 发送短信
     * @param smsParam 发送短信的参数
     * @return 渠道商接口返回值
     */
    List<SmsRecord> send(SmsParam smsParam) throws Exception;
}

