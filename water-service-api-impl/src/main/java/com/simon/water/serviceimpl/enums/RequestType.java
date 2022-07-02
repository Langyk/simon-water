package com.simon.water.serviceimpl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RequestType {

    SINGLE(10,"请求接口为single类型"),

    BATCH(20,"请求接口为batch类型");

    /**
     * code
     */
    private Integer code;
    /**
     * 类型说明
     */
    private String description;
}
