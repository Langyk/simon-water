package com.simon.water.serviceimpl.domain;

import com.simon.water.common.domain.TaskInfo;
import com.simon.water.pipeline.ProcessModel;
import com.simon.water.serviceapi.domain.MessageParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/6/29 21:21
 *发送消息任务类型
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 发送任务消息
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;


    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;

}

