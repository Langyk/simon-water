package com.simon.water.serviceimpl.action;

/**
 * creat by 郎亚坤
 * 2022/7/2 10:21
 */

import cn.hutool.core.collection.CollUtil;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.serviceapi.domain.MessageParam;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 前置参数校验
 */

@Slf4j
public class PreParamAction implements BusinessProcess {


    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        if(messageTemplateId == null || CollUtil.isEmpty(messageParamList)){
            context.setNeedBreak(true);
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));

        }
    }
}

