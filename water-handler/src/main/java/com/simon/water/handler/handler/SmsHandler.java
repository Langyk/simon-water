package com.simon.water.handler.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.simon.water.common.dto.model.SmsContentModel;
import com.simon.water.handler.domain.SmsParam;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.dao.SmsRecordDao;
import com.simon.water.domain.SmsRecord;
import com.simon.water.handler.script.SmsScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean doHandler(TaskInfo taskInfo) {
        SmsContentModel smsContentModel = (SmsContentModel) taskInfo.getContentModel();
        String resultContent;
        if (StrUtil.isNotBlank(smsContentModel.getUrl())) {
            resultContent = smsContentModel.getContent() + " " + smsContentModel.getUrl();
        } else {
            resultContent = smsContentModel.getContent();
        }

        SmsParam smsParam = SmsParam.builder()
                .phones(taskInfo.getReceiver())
                .content(resultContent)
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .supplierId(10) // ChannelType
                .supplierName("腾讯云通知类消息渠道").build();

        List<SmsRecord> recordList = smsScript.send(smsParam);

        if (CollUtil.isNotEmpty(recordList)) {
            recordList.forEach(singleRecord -> {
                smsRecordDao.insert(singleRecord);
            });
            return true;
        }
        return false;
    }
}

