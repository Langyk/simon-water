package com.simon.water.serviceimpl.service;

import cn.monitor4all.logRecord.annotation.OperationLog;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.pipeline.ProcessController;
import com.simon.water.serviceapi.domain.BatchSendRequest;
import com.simon.water.serviceapi.domain.SendRequest;
import com.simon.water.serviceapi.domain.SendResponse;
import com.simon.water.serviceapi.service.SendService;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 发送接口
 * creat by 郎亚坤
 * 2022/6/26 17:09
 */

@Service
public class SendServiceImpl implements SendService {

    /**
     * 通过自定义注解配置的方式注入注解
     */
    @Autowired
    private ProcessController processController;

    @Override
    @OperationLog(bizType = "SendService#send", bizId = "#sendRequest.messageTemplateId", msg = "#sendRequest")
    public SendResponse send(SendRequest sendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Arrays.asList(sendRequest.getMessageParam()))
                .build();


        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getCode(), process.getResponse().getMsg());
    }

    @Override
    @OperationLog(bizType = "SendService#batchSend", bizId = "#batchSendRequest.messageTemplateId", msg = "#batchSendRequest")
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getCode(), process.getResponse().getMsg());
    }

}

