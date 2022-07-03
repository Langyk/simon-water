package com.simon.water.serviceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * creat by 郎亚坤
 * 2022/6/29 21:54
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {

    /** 普通发送流程 */
    COMMON_SEND("send","普通发送"),

    /** 撤回流程 */
    RECALL("send", "撤回消息");

     /** code 关联责任链的模板 */
    private String code;

    /** 类型说明 */
    private String description;
}

