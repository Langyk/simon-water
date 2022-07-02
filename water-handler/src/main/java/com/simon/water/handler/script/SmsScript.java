package com.simon.water.handler.script;

import com.simon.water.handler.domain.SmsParam;
import com.simon.water.domain.SmsRecord;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/6/20 20:13
 */
@Component
public interface SmsScript {

    List<SmsRecord> send(SmsParam smsParam);
}

