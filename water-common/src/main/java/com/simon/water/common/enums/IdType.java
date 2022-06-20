package com.simon.water.common.enums;


/**
 * 发送消息的个人信息
 */
public enum IdType {

    USER_ID(10,"userid"),
    DID(20,"did"),
    PHONE(30,"phone"),
    OPEN_ID(40,"openId"),
    EMAIL(50,"email");

    private Integer code;
    private String description;

    IdType(Integer code, String description){
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
