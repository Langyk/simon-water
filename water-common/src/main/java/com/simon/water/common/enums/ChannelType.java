package com.simon.water.common.enums;

/**
 * creat by 郎亚坤
 * 2022/6/19 22:33
 */

/**
 * 发送渠道类型枚举
 */
public enum ChannelType {

    IM(10,"IM(站内信)"),
    PUSH(20,"PUSH(通知栏)"),
    SMS(30,"SMS(短信)"),
    EMAIL(40,"EMAIL(邮件)"),
    OFFICIAL_ACCOUNT(50,"officialAccounts(服务号)"),
    MINI_PROGRAM(60,"miniProgram(小程序)");

    private Integer code;
    private String description;

    ChannelType(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

