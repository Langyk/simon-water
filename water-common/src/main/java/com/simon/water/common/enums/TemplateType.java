package com.simon.water.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * creat by 郎亚坤
 * 2022/6/20 18:58
 * 模板枚举信息
 */

@Getter
@ToString
@AllArgsConstructor
public enum TemplateType {

    OPERATION(10, "运营类的模板"),
    TECHNOLOGY(20, "技术类的模板");

    private Integer code;
    private String description;


}

