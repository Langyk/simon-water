package com.simon.water.common.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RespStatusEnum {

    /**
     * 操作
     */
    SUCCESS("00000", "操作成功"),
    FAIL("00001", "操作失败"),


    /**
     * 客户端
     */
    CLIENT_BAD_PARAMETERS("A0100", "客户端参数错误"),

    /**
     * 系统
     */
    SERVICE_ERROR("B0001", "服务执行异常"),
    RESOURCE_NOT_FOUND("B0404", "资源不存在");

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

}
