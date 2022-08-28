package com.simon.water.utils;

import cn.monitor4all.logRecord.bean.LogDTO;
import cn.monitor4all.logRecord.service.CustomLogListener;
import com.alibaba.fastjson.JSON;
import com.simon.water.common.domain.AnchorInfo;
import com.simon.water.common.domain.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * creat by 郎亚坤
 * 2022/8/5 22:10
 */

/**
 * 所有日志凑存在
 */
@Slf4j
@Component
public class LogUtils extends CustomLogListener {


    /**
     * 方法切面日志，@OperationLog所产生
     */
    @Override
    public void createLog(LogDTO logDTO) throws Exception {
        log.info(JSON.toJSONString(logDTO));
    }

    /**
     * 记录当前对象信息
     */
    public static void print(LogParam logParam){
        logParam.setTimestamp(System.currentTimeMillis());
        log.info(JSON.toJSONString(logParam));
    }

    /**
     * 记录打点信息
     */
    public static void print(AnchorInfo anchorInfo){
        anchorInfo.setTimestamp(System.currentTimeMillis());
        log.info(JSON.toJSONString(anchorInfo));
    }

    /**
     * 记录当前对象信息和打点信息
     */
    public static void print(LogParam logParam,AnchorInfo anchorInfo){
        print(anchorInfo);
        print(logParam);
    }
}

