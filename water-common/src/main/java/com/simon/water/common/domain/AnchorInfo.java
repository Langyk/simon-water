package com.simon.water.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * creat by 郎亚坤
 * 2022/8/2 22:02
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnchorInfo {

    /**
     * 发送用户
     */
    private Set<String> ids;

    /**
     * 具体点位
     */
    private int state;

    /**
     * 业务id(链路追踪时使用)
     * 生成逻辑参考 TaskInfoUtils
     */
    private Long businessId;

    /**
     * 生成时间
     */
    private long timestamp;
}

