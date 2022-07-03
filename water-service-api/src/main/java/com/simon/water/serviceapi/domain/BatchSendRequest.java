package com.simon.water.serviceapi.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/6/29 21:49
 * 发送接口参数
 * batch
 */
@Data
@Accessors(chain = true)
public class BatchSendRequest {

    /**
     * 执行业务类型
     * 必传
     */
    private String code;

    /**
     * 消息模板Id
     * 必传
     */
    private Long messageTemplateId;

    /**
     * 消息相关参数
     * 必传
     */
    private List<MessageParam> messageParamList;

}

