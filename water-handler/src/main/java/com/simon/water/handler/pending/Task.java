package com.simon.water.handler.pending;

import cn.hutool.core.collection.CollUtil;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.handler.handler.HandlerHolder;
import com.simon.water.handler.service.deduplication.DeduplicationRuleService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * creat by 郎亚坤
 * 2022/7/16 11:31
 *
 * Task 执行器
 * 0. 丢弃消息
 * 1. 通用去重功能
 * 2. 发送消息
 */

@Data
@Accessors(chain = true)
@Slf4j
public class Task implements Runnable{


    @Autowired
    private HandlerHolder handlerHolder;

    @Autowired
    private DeduplicationRuleService deduplicationRuleService;

    private TaskInfo taskInfo;


    @Override
    public void run() {
        // 0. 丢弃消息 TODO: 2022/7/17

        // 1.通用消息去重 TODO: 2022/7/16
        deduplicationRuleService.duplication(taskInfo);
        // 2. 真正发送和消息
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            handlerHolder.route(taskInfo.getSendChannel())
                    .doHandler(taskInfo);
        }
    }
}

