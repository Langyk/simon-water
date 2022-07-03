package com.simon.water.handler.handler;

import com.simon.water.common.domain.TaskInfo;

/**
 * creat by 郎亚坤
 * 2022/6/20 19:12
 *
 * 发送各个渠道的handler
 */
public interface Handler{


    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    boolean doHandler(TaskInfo taskInfo);
}

