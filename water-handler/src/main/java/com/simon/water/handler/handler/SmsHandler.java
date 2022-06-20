package com.simon.water.handler.handler;

import cn.hutool.core.collection.CollUtil;
import com.simon.water.common.pojo.SmsParam;
import com.simon.water.common.pojo.TaskInfo;
import com.simon.water.dao.SmsRecordDao;
import com.simon.water.domain.SmsRecord;
import com.simon.water.handler.script.SmsScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/6/20 19:13
 */

@Component
public class SmsHandler implements Handler {

    @Autowired
    private SmsRecordDao smsRecordDao;

    @Autowired
    private SmsScript smsScript;

    @Override
    public boolean doHandler(TaskInfo taskInfo) {
        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(taskInfo.getContent())
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .supplierId(10) // ChannelType
                .supplierName("腾讯云通知类消息渠道").build();

        List<SmsRecord> recordList = smsScript.send(smsParam);

        if(CollUtil.isNotEmpty(recordList)){
            smsRecordDao.saveAll(recordList);
            return true;

        }
        return false;
    }
}

