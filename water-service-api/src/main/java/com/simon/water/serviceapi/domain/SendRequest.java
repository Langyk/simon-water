package com.simon.water.serviceapi.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送接口的参数
 * creat by 郎亚坤
 * 2022/6/26 17:16
 */
@Data
@Accessors(chain = true)
public class SendRequest {

    /**
     * 执行业务类型,
     * COMMON_CODE:普通发送
     */
    private String code;

    /**
     * 消息模板id
     */
    private Long messageTemplateId;

    /**
     * 消息相关参数
     */
    private MessageParam messageParam;

}

