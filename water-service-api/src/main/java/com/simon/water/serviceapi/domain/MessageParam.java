package com.simon.water.serviceapi.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/6/29 21:43
 * 消息参数
 * single
 */

@Data
@Accessors(chain = true)
public class MessageParam {

    /**
     * 接收者，多个用，逗号分开
     * 必传
     */
    private String receiver;

    /**
     * 消息内容中的可变部分
     * 可选
     */
    private Map<String,String> variables;

    /**
     * 扩展参数
     * 可选
     */
    private Map<String,String> extra;

}

