package com.simon.water.serviceimpl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.enums.ChannelType;
import com.simon.water.common.enums.IdType;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * creat by 郎亚坤
 * 2022/7/2 23:18
 */

@Slf4j
public class AfterParamCheckAction implements BusinessProcess {

    /** 定义上手机号的正则表达式 */
    private static final String PHONE_REGEX_EXP = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();
        // 1. 过滤掉不合法的手机号
        filterIllegalPhoneNum(taskInfo);

        // 2.

        if (CollUtil.isEmpty(taskInfo)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

    }


    /**
     * 如果指定类型是手机号，且渠道是发送短信，检测输入手机号是否合法
     * @param taskInfo
     */
    private void filterIllegalPhoneNum(List<TaskInfo> taskInfo) {
        Integer idType = taskInfo.get(0).getIdType();
        Integer sendChannel = taskInfo.get(0).getSendChannel();

        if (IdType.PHONE.getCode().equals(idType) && ChannelType.SMS.getCode().equals(sendChannel)) {
            Iterator<TaskInfo> iterator = taskInfo.iterator();

            // 利用正则找出不合法的手机号
            while (iterator.hasNext()) {
                TaskInfo task = iterator.next();
                Set<String> illegalPhone = task.getReceiver().stream()
                        .filter(phone -> !ReUtil.isMatch(PHONE_REGEX_EXP, phone))
                        .collect(Collectors.toSet());

                if (CollUtil.isNotEmpty(illegalPhone)) {
                    task.getReceiver().removeAll(illegalPhone);
                    log.error("{} find illegal phone!{}", task.getMessageTemplateId(), JSON.toJSONString(illegalPhone));
                }
                if (CollUtil.isEmpty(task.getReceiver())) {
                    iterator.remove();
                }
            }
        }
    }
}

