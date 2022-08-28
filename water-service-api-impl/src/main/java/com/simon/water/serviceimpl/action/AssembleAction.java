package com.simon.water.serviceimpl.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.constant.WaterConstant;
import com.simon.water.common.enums.ChannelType;
import com.simon.water.common.dto.model.ContentModel;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.dao.MessageTemplateDao;
import com.simon.water.domain.MessageTemplate;
import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.serviceapi.domain.MessageParam;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import com.simon.water.utils.ContentHolderUtil;
import com.simon.water.utils.TaskInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 拼装参数
 */
@Slf4j
public class AssembleAction implements BusinessProcess {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel  = (SendTaskModel) context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        /** 1. 判断模板是否存在 */
        try {
            Optional<MessageTemplate> messageTemplate = Optional.ofNullable(messageTemplateDao.selectById(messageTemplateId));
            if(messageTemplate.isEmpty() || messageTemplate.get().getIsDeleted().equals(WaterConstant.TRUE)){
                context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
                return;
            }
            /** 2. 组装TaskInfo信息 */
            List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel, messageTemplate.get());
            sendTaskModel.setTaskInfo(taskInfos);

        }catch (Exception e){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("assemble task fail! templatId:{}, e:{}",messageTemplateId, Throwables.getStackTraceAsString(e));
        }
    }

    /**
     * 组装 TaskInfo信息
     */
    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate){
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList){
            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBussinessId(messageTemplate.getId(),messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(String.valueOf(StrUtil.C_COMMA)))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate, messageParam))
//                    .deduplicationTime(messageTemplate.getDeduplicationTime())
//                    .isNightShield(messageTemplate.getIsNightShield())
                    .build();
            taskInfoList.add(taskInfo);
        }
        return taskInfoList;
    }

    /**
     * 获取contentModel，替换模板msgContent中占位符信息
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam){
        // 获取真正的ContentModel类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class contentModelClass = ChannelType.getChanelModelClassByCode(sendChannel);

        //得到模板的msgContent 和 入参
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());

        /**
         * 反射得到不同渠道对应的值
         */
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(contentModelClass);
        for (Field field : fields){
            String originValue = jsonObject.getString(field.getName());
            if (StrUtil.isNotBlank(originValue)) {
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                // 替换值
                ReflectUtil.setFieldValue(contentModel, field, resultValue);
            }
        }

        // 如果url字段存在，则在url拼接对应的埋点参数
        String url = (String) ReflectUtil.getFieldValue(contentModel,"url");
        if(StrUtil.isNotBlank(url)){
            String resultUrl = TaskInfoUtils.generateUrl(url,messageTemplate.getId(),messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel,"url",resultUrl);
        }
        return contentModel;
    }
    public static void main(String[] args) {

        MessageTemplate messageTemplate = MessageTemplate.builder()
                .sendChannel(ChannelType.SMS.getCode())
                .msgContent("{\"url\":\"www.baidu.com/{$urlParam}\",\"content\":\"{$contentValue}\"}").build();
        HashMap<String, String> map = new HashMap<>();
        map.put("urlParam", "2222");
        map.put("contentValue", "3333");
        MessageParam messageParam = new MessageParam().setVariables(map);

        ContentModel contentModelValue = getContentModelValue(messageTemplate, messageParam);
        System.out.println(JSON.toJSONString(contentModelValue));
    }

}
