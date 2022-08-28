package com.simon.water.handler.domain;

import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.enums.AnchorState;
import lombok.Builder;
import lombok.Data;

/**
 * creat by 郎亚坤
 * 2022/7/24 11:10
 */

/**
 * 去重服务所需要的参数
 */
@Builder
@Data
public class DeduplicationParam {

    /**
     * TaskInfo信息
     */
    private TaskInfo taskInfo;

    /**
     * 去重时间
     * 单位：秒
     */
    private Long deduplicationTime;

    /**
     * 去重达到的次数
     */
    private Integer countNum;

    /**
     * 标识属于那种去重
     */
    private AnchorState anchorState;

}

