package com.simon.water.serviceimpl.action;

/**
 * creat by 郎亚坤
 * 2022/7/2 10:21
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.serviceapi.domain.MessageParam;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前置参数校验
 */

@Slf4j
public class PreParamCheckAction implements BusinessProcess {


    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        // 没有传入 消息模板Id 或者 messageParam
        if(messageTemplateId == null || CollUtil.isEmpty(messageParamList)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;

        }

        // 过滤receiver=null的messageParam
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());
        if(CollUtil.isEmpty(resultMessageParamList)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }
        sendTaskModel.setMessageParamList(resultMessageParamList);

    }
}

