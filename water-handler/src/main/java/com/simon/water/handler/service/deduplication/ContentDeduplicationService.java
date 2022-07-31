package com.simon.water.handler.service.deduplication;

/**
 * creat by 郎亚坤
 * 2022/7/24 14:10
 */

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.simon.water.common.domain.TaskInfo;
import org.springframework.stereotype.Service;

/**
 * 内容去重复服务（默认5分钟相同的文案发给相同的用户去重）
 */
@Service
public class ContentDeduplicationService extends AbstractDeduplicationService{

    /**
     * 内容去重 构建key
     * <p>
     * key: md5(templateId + receiver + content)
     * <p>
     * 相同的内容相同的模板短时间内发给同一个人
     *
     * @param taskInfo
     * @return
     */
    @Override
    public String deduplicationSingleKey(TaskInfo taskInfo, String receiver) {
        return DigestUtil.md5Hex(taskInfo.getMessageTemplateId() + receiver
                + JSON.toJSONString(taskInfo.getContentModel()));
    }

}

