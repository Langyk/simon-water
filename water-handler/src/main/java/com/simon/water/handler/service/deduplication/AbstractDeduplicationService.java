package com.simon.water.handler.service.deduplication;

/**
 * creat by 郎亚坤
 * 2022/7/24 11:15
 */

import cn.hutool.core.collection.CollUtil;
import com.simon.water.common.constant.WaterConstant;
import com.simon.water.common.domain.TaskInfo;
import com.simon.water.handler.domain.DeduplicationParam;
import com.simon.water.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 去重服务
 */
public abstract class AbstractDeduplicationService {

    @Autowired
    private RedisUtils redisUtils;

    public void deduplication(DeduplicationParam param){
        TaskInfo taskInfo = param.getTaskInfo();
        Set<String> filterReceiver = new HashSet<>(taskInfo.getReceiver().size());

        // 获取redis记录
        Set<String> readyPutRedisReceiver = new HashSet<>(taskInfo.getReceiver().size());
        List<String> keys = deduplicationAllKey(taskInfo);
        Map<String, String> inRedisValue = redisUtils.mGet(keys);

        for (String receiver : taskInfo.getReceiver()) {
            String key = deduplicationSingleKey(taskInfo, receiver);
            String value = inRedisValue.get(key);

            // 符合条件的用户
            if (value != null && Integer.valueOf(value) >= param.getCountNum()) {
                filterReceiver.add(receiver);
            } else {
                readyPutRedisReceiver.add(receiver);
            }
        }

        // 不符合条件的用户：需要更新Redis(无记录添加，有记录则累加次数)
        putInRedis(readyPutRedisReceiver, inRedisValue, param);

        // 剔除符合去重条件的用户
        taskInfo.getReceiver().removeAll(filterReceiver);
    }

    /**
     * 构建去重key
     * @param taskInfo
     * @param receiver
     * @return
     */
    protected abstract String deduplicationSingleKey(TaskInfo taskInfo, String receiver);


    /**
     * 存入redis 实现去重
     *
     * @param readyPutRedisReceiver
     */
    private void putInRedis(Set<String> readyPutRedisReceiver,
                            Map<String, String> inRedisValue, DeduplicationParam param) {
        Map<String, String> keyValues = new HashMap<>(readyPutRedisReceiver.size());
        for (String receiver : readyPutRedisReceiver) {
            String key = deduplicationSingleKey(param.getTaskInfo(), receiver);
            if (inRedisValue.get(key) != null) {
                keyValues.put(key, String.valueOf(Integer.valueOf(inRedisValue.get(key)) + 1));
            } else {
                keyValues.put(key, String.valueOf(WaterConstant.TRUE));
            }
        }
        if (CollUtil.isNotEmpty(keyValues)) {
            redisUtils.pipelineSexEX(keyValues, param.getDeduplicationTime());
        }
    }

    /**
     * 获取得到当前消息模板所有的去重Key
     *
     * @param taskInfo
     * @return
     */
    private List<String> deduplicationAllKey(TaskInfo taskInfo) {
        List<String> result = new ArrayList<>(taskInfo.getReceiver().size());
        for (String receiver : taskInfo.getReceiver()) {
            String key = deduplicationSingleKey(taskInfo, receiver);
            result.add(key);
        }
        return result;
    }
}

