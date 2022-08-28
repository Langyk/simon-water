package com.simon.water.handler.script;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.simon.water.common.enums.SmsStatus;
import com.simon.water.handler.domain.SmsParam;
import com.simon.water.domain.SmsRecord;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * creat by 郎亚坤
 * 2022/6/18 23:30
 */


@Slf4j
@Service
public class TencentSmsScript implements SmsScript {

    @Autowired

    /**
     * api相关
     */
    private static final String URL = "sms.tencentcloudapi.com";
    private static final String REGION = "ap-guangzhou";
    private static final Integer PHONE_NUM = 11;


    /**
     * 账号相关
     */
    @Value("${tencent.sms.account.secret-id}")
    private String SECRET_ID;

    @Value("${tencent.sms.account.secret-key}")
    private String SECRET_KEY;

    @Value("${tencent.sms.account.sms-sdk-app-id}")
    private String SMS_SDK_APP_ID;

    @Value("${tencent.sms.account.template-id}")
    private String TEMPLATE_ID;

    @Value("${tencent.sms.account.sign_name}")
    private String SIGN_NAME;


    @Override
    public List<SmsRecord> send(SmsParam smsParam) throws TencentCloudSDKException{

            // 1. 初始化 client
            SmsClient client = init();

            // 2. 组装请求参数
            SendSmsRequest request = assembleReq(smsParam);

            // 3. 发送请求
            SendSmsResponse response = client.SendSms(request);

            // 4. 获取短信回执
            return assembleSmsRecord(smsParam, response);



    }


    private List<SmsRecord> assembleSmsRecord(SmsParam smsParam, SendSmsResponse response) {
        if (response == null || ArrayUtil.isEmpty(response.getSendStatusSet())) {
            return null;
        }

        List<SmsRecord> smsRecordList = new ArrayList<>();

        for (SendStatus sendStatus : response.getSendStatusSet()) {
            String phone = new StringBuilder(new StringBuilder(sendStatus.getPhoneNumber())
                    .reverse().substring(0, PHONE_NUM)).reverse().toString();

            /**
             * {
             *   "Response": {
             *     "RequestId": "d1eedfe7-e3ef-495f-9be5-f92300914fb1",
             *     "SendStatusSet": [
             *       {
             *         "Code": "Ok",
             *         "Fee": 1,
             *         "IsoCode": "CN",
             *         "Message": "send success",
             *         "PhoneNumber": "+8619802110062",
             *         "SerialNo": "2645:347560053616557347421191006",
             *         "SessionContext": ""
             *       }
             *     ]
             *   }
             * }
             */
            SmsRecord smsRecord = SmsRecord.builder()
                    .sendDate(Integer.valueOf(DateUtil.format(new Date(), "yyyyMMdd")))
                    .messageTemplateId(smsParam.getMessageTemplateId())
                    .phone(Long.valueOf(phone))
                    .supplierId(smsParam.getSupplierId())
                    .supplierName(smsParam.getSupplierName())
                    .seriesId(sendStatus.getSerialNo())
                    .chargingNum(Math.toIntExact(sendStatus.getFee()))
                    .status(SmsStatus.SEND_SUCCESS.getCode())
                    .reportContent(sendStatus.getCode())
                    .created(Math.toIntExact(DateUtil.currentSeconds()))
                    .updated(Math.toIntExact(DateUtil.currentSeconds()))
                    .build();

            smsRecordList.add(smsRecord);
        }
        return smsRecordList;
    }

    /**
     * 组装发送短信参数
     */
    private SendSmsRequest assembleReq(SmsParam smsParam) {
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = smsParam.getPhones().toArray(new String[smsParam.getPhones().size() - 1]);
        req.setPhoneNumberSet(phoneNumberSet1);
        req.setSmsSdkAppId(SMS_SDK_APP_ID);
        req.setSignName(SIGN_NAME);
        req.setTemplateId(TEMPLATE_ID);
        String[] templateParamSet1 = {smsParam.getContent()};
        req.setTemplateParamSet(templateParamSet1);
        req.setSessionContext(IdUtil.fastSimpleUUID());
        return req;
    }

    /**
     * 初始化 client
     */
    private SmsClient init() {
        // 1. 实例化一个对象cred
        Credential cred = new Credential(SECRET_ID, SECRET_KEY);
        // 2. 实例化一个http选项
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(URL);
        // 3. 实例化一个client选项
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 4. 实例化一个要请求产品的client对象
        SmsClient client = new SmsClient(cred, REGION, clientProfile);
        return client;
    }
}

