package com.simon.water.handler.service.deduplication;

import cn.hutool.core.date.DateUtil;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.handler.domain.DeduplicationParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * creat by 郎亚坤
 * 2022/7/24 14:18
 *
 * 去重服务
 */
public class DeduplicationRuleService {

    @Autowired
    private ContentAbstractDeduplicationService contentDeduplicationService;

    @Autowired
    private FrequencyDeduplicationService frequencyDeduplicationService;


    public void duplication(TaskInfo taskInfo) {

        // 文案去重
        DeduplicationParam contentParams = DeduplicationParam.builder()
                .deduplicationTime(300L).countNum(1).taskInfo(taskInfo)
                .build();
        contentDeduplicationService.deduplication(contentParams);

        // 运营总规则去重(一天内用户收到最多同一个渠道的消息次数)
        Long seconds = (DateUtil.endOfDay(new Date()).getTime() - DateUtil.current()) / 1000;
        DeduplicationParam businessParams = DeduplicationParam.builder()
                .deduplicationTime(seconds).countNum(5).taskInfo(taskInfo)
                .build();
        frequencyDeduplicationService.deduplication(businessParams);
    }

}

