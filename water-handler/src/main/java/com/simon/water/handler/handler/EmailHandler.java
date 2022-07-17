package com.simon.water.handler.handler;

import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.enums.ChannelType;
import org.springframework.stereotype.Component;

/**
 * creat by 郎亚坤
 * 2022/7/16 11:52
 *
 * 邮件发送消息处理
 */

@Component
public class EmailHandler extends Handler{


    public EmailHandler() {
        channelCode = ChannelType.EMAIL.getCode();
    }


    @Override
    public void handler(TaskInfo taskInfo) {

    }
}

