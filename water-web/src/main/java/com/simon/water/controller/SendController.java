package com.simon.water.controller;

import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.handler.handler.SmsHandler;
import com.simon.water.serviceapi.domain.MessageParam;
import com.simon.water.serviceapi.domain.SendRequest;
import com.simon.water.serviceapi.domain.SendResponse;
import com.simon.water.serviceapi.enums.BusinessCode;
import com.simon.water.serviceapi.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/6/20 21:17
 */

@RestController
public class SendController {

    @Autowired
    private SmsHandler smsHandler;

    @Autowired
    private SendService sendService;

    /**
     * 测试发送短信
     * @param phone 手机号
     * @return
     */
//    @GetMapping("/sendSms")
//    public BasicResultVO sendSms(String phone,String content,Long messageTemplateId ) {
//
//        TaskInfo taskInfo = TaskInfo.builder()
//                .receiver(new HashSet<>(Arrays.asList(phone)))
//                .content(content)
//                .messageTemplateId(messageTemplateId).build();
//
//        if(smsHandler.doHandler(taskInfo)){
//
//            return BasicResultVO.success("信息发送成功");
//        }
//
//        return BasicResultVO.fail("信息发送失败");
//
//
//
//    }
    @GetMapping("/sendSmsV2")
    public SendResponse sendSmsV2(String phone) {
        /**
         *
         * messageTemplate Id 为1 的模板内容
         * {"auditStatus":10,"auditor":"yyyyyyz","created":1636978066,"creator":"yyyyc","deduplicationTime":1,"expectPushTime":"0","flowId":"yyyy","id":1,"idType":20,"isDeleted":0,"isNightShield":0,"msgContent":"{\"content\":\"{$contentValue}\"}","msgStatus":10,"msgType":10,"name":"test短信","proposer":"yyyy22","sendAccount":66,"sendChannel":30,"team":"yyyt","templateType":10,"updated":1636978066,"updator":"yyyyu"}
         *
         */

        // 文案参数
        Map<String, String> variables = new HashMap<>();
        variables.put("content", "4567");
        MessageParam messageParam = new MessageParam().setReceiver(phone).setVariables(variables);
        // ID为30001的消息模板
        SendRequest sendRequest = new SendRequest().setCode(BusinessCode.COMMON_SEND.getCode())
                .setMessageTemplateId(30001L)
                .setMessageParam(messageParam);

        SendResponse response = sendService.send(sendRequest);

        return response;
    }

}

