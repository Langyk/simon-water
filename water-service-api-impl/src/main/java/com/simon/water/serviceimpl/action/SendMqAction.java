package com.simon.water.serviceimpl.action;

/**
 * creat by 郎亚坤
 * 2022/7/2 10:30
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.vo.BasicResultVO;
import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessContext;
import com.simon.water.serviceimpl.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 将消息发送到MQ
 */

@Slf4j
public class SendMqAction implements BusinessProcess {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${water.topic.name}")
    private String topicName;

    @Override
    public void process(ProcessContext context) {
       SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
       try {
           //序列化的时候需要把“类信息”写进去，保证反序列化的时候可可以拿到子类的信息
           kafkaTemplate.send(topicName, JSON.toJSONString(sendTaskModel.getTaskInfo(),
                   new SerializerFeature[] {SerializerFeature.WriteClassName}));
       }catch (Exception e){
           context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
           log.error("send kafka fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                   , JSON.toJSONString(sendTaskModel.getTaskInfo().get(0)));
       }
    }
}

