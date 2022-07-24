package com.simon.water.handler.service.deduplication;

import cn.hutool.core.util.StrUtil;
import com.simon.water.common.domain.TaskInfo;

/**
 * creat by 郎亚坤
 * 2022/7/24 14:18
 *
 * 频次去重服务
 */
public class FrequencyDeduplicationService extends AbstractDeduplicationService{

    private static final String PREFIX = "FRE";

    /**
     * 业务规则去重 构建key
     * <p>
     * key ： receiver + templateId + sendChannel
     * <p>
     * 一天内一个用户只能收到某个渠道的消息 N 次
     *
     * @param taskInfo
     * @param receiver
     * @return
     */
    @Override
    public String deduplicationSingleKey(TaskInfo taskInfo, String receiver) {
        return PREFIX + StrUtil.C_UNDERLINE
                + receiver  + StrUtil.C_UNDERLINE
                + taskInfo.getMessageTemplateId() + StrUtil.C_UNDERLINE
                + taskInfo.getSendChannel();
    }
}

