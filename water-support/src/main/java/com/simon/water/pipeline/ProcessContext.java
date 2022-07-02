package com.simon.water.pipeline;

import com.simon.water.common.vo.BasicResultVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * creat by 郎亚坤
 * 2022/7/1 14:27
 */

/**
 *流程上下文
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext<T extends ProcessModel> {

    /**
     * 责任链中断标识
     */
    private Boolean needBreak ;


    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 流程处理结果
     */
    BasicResultVO response;

    /**
     * 存储责任链上下文数据的模型
     */
    private T processModel;
}

