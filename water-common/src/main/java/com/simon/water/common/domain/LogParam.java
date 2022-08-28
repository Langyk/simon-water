package com.simon.water.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * creat by 郎亚坤
 * 2022/8/2 22:09
 */


/**
 * 日志参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogParam {

    /**
     * 需要记录的日志
     */
    private Object object;

    /**
     * 标识日志的业务
     */
    private String bizType;

    /**
     * 生成时间
     */
    private long timestamp;

}

