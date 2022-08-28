package com.simon.water.handler.receiver;

/**
 * creat by 郎亚坤
 * 2022/7/16 16:39
 */

import com.alibaba.fastjson.JSON;
import com.simon.water.common.domain.AnchorInfo;
import com.simon.water.common.domain.LogParam;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.common.enums.AnchorState;
import com.simon.water.handler.pending.Task;
import com.simon.water.handler.pending.TaskPendingHolder;
import com.simon.water.handler.utils.GroupIdMappingUtils;
import com.simon.water.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.Optional;

/**
 * 消费MQ中的消息
 */

@Slf4j
public class Receiver {

    private static final String LOG_BIZ_TYPE = "Receiver#consumer";

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TaskPendingHolder taskPendingHolder;

    @KafkaListener(topics = "#{'${water.topic.name}'}")
    public void consumer(ConsumerRecord<?, String> consumerRecord, @Header(KafkaHeaders.GROUP_ID) String topicGroupId) {
        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            List<TaskInfo> TaskInfoLists = JSON.parseArray(kafkaMessage.get(), TaskInfo.class);
            String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(TaskInfoLists.get(0));
            log.info("-----------start---------");
            log.info(messageGroupId);
            log.info("-----------end-----------");
            /**
             * 每个消费者组 只消费 他们自身关心的消息
             */
            if (topicGroupId.equals(messageGroupId)) {
                for (TaskInfo taskInfo : TaskInfoLists) {
                    LogUtils.print(LogParam.builder().bizType(LOG_BIZ_TYPE).object(taskInfo).build(),
                            AnchorInfo.builder().ids(taskInfo.getReceiver()).businessId(taskInfo.getBusinessId()).state(AnchorState.RECEIVE.getCode()).build());
                    Task task = context.getBean(Task.class).setTaskInfo(taskInfo);
                    taskPendingHolder.route(topicGroupId).execute(task);
                }
            }
        }

    }
}

