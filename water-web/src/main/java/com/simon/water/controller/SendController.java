package com.simon.water.controller;

import com.simon.water.handler.handler.SmsHandler;
import com.simon.water.serviceapi.domain.MessageParam;
import com.simon.water.serviceapi.domain.SendRequest;
import com.simon.water.serviceapi.domain.SendResponse;
import com.simon.water.serviceapi.enums.BusinessCode;
import com.simon.water.serviceapi.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
     * @param templateId 模板id
     * @return
     */
    @GetMapping("/sendSms")
    public SendResponse sendSmsTest(String phone,Long templateId) {
        /**
         *
         * messageTemplate Id 为1 的模板内容（普通短信）
         * {"auditStatus":10,"auditor":"yyyyyyz","created":1636978066,"creator":"yyyyc","deduplicationTime":1,"expectPushTime":"0","flowId":"yyyy","id":1,"idType":30,"isDeleted":0,"isNightShield":0,"msgContent":"{\"content\":\"{$contentValue}\"}","msgStatus":10,"msgType":10,"name":"test短信","proposer":"yyyy22","sendAccount":66,"sendChannel":30,"team":"yyyt","templateType":10,"updated":1636978066,"updator":"yyyyu"}
         *
         * messageTemplate Id 为2 的模板内容（营销短信）
         * {"auditStatus":10,"auditor":"yyyyyyz","created":1636978066,"creator":"yyyyc","deduplicationTime":1,"expectPushTime":"0","flowId":"yyyy","id":1,"idType":30,"isDeleted":0,"isNightShield":0,"msgContent":"{\"content\":\"{$contentValue}\"}","msgStatus":10,"msgType":20,"name":"test短信","proposer":"yyyy22","sendAccount":66,"sendChannel":30,"team":"yyyt","templateType":10,"updated":1636978066,"updator":"yyyyu"}
         */

        // 文案参数
        Map<String,String> variables = new HashMap<>(8);
        variables.put("content","6666");

        MessageParam messageParam = new MessageParam().setReceiver(phone).setVariables(variables);
        SendRequest sendRequest = new SendRequest().setCode(BusinessCode.COMMON_SEND.getCode())
                .setMessageTemplateId(templateId)
                .setMessageParam(messageParam);

        return sendService.send(sendRequest);


    }



    @GetMapping("/sendSmsV2")
    public SendResponse sendSmsV2(String phone) {
        /**
         *
         * messageTemplate Id 为1 的模板内容
         * {"auditStatus":10,"auditor":"yyyyyyz","created":1636978066,"creator":"yyyyc","deduplicationTime":1,"expectPushTime":"0","flowId":"yyyy","id":1,"idType":20,"isDeleted":0,"isNightShield":0,"msgContent":"{\"content\":\"{$contentValue}\"}","msgStatus":10,"msgType":10,"name":"test短信","proposer":"yyyy22","sendAccount":66,"sendChannel":30,"team":"yyyt","templateType":10,"updated":1636978066,"updator":"yyyyu"}
         *
         */

        // 文案参数
        Map<String, String> variables = new HashMap<>(8);
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

