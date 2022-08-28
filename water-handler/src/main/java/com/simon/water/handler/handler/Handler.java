package com.simon.water.handler.handler;

import com.simon.water.common.domain.AnchorInfo;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.enums.AnchorState;
import com.simon.water.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * creat by 郎亚坤
 * 2022/6/20 19:12
 *
 * 发送各个渠道的handler
 */
public abstract class Handler{
    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;



    @Autowired
    private HandlerHolder handlerHolder;

    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode,this);
    }

    // 正常和异常情况下的日志处理
    public void doHandler(TaskInfo taskInfo) {
        if(!handler(taskInfo)){
            LogUtils.print(AnchorInfo.builder().state(AnchorState.SEND_FAIL.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
        }
        LogUtils.print(AnchorInfo.builder().state(AnchorState.SEND_SUCCESS.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
    }

    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);
}

