package com.simon.water.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.val;


/**
 * 发送的消息类型
 */
@Getter
@ToString
@AllArgsConstructor
public enum MessageType {

    NOTICE(10,"通知类消息","notice"),
    MARKETING(20,"营销类消息","marketing"),
    AUTH_CODE(30,"验证码消息","auth_code");

    /**
     * 编码值
     */
    private Integer code;

    /**
     * 描述
     */
    private String description;

    /**
     * 英文表示
     */
    private String code_en;


    public static MessageType getEnumByCode(Integer code){
        MessageType[] values = values();
        for (MessageType value : values){
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return null;
    }

}
