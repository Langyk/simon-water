package com.simon.water.utils;

import cn.hutool.core.date.DateUtil;
import com.simon.water.common.constant.WaterConstant;

import java.util.Date;

/**
 * creat by 郎亚坤
 * 2022/7/1 16:30
 *
 * 生成消息推送URL工具类
 */
public class TaskInfoUtils {

    private static int TYPE_FLAG = 100;

    public static Long generateBussinessId(Long templateId, Integer templateType){
        String cutValue = Long.valueOf(templateId).toString().substring(0,3);
        Integer today = Integer.valueOf(DateUtil.format(new Date(), WaterConstant.YYYYMMDD));
        return Long.valueOf(cutValue + String.format("%d%s", templateType * TYPE_FLAG, today));
    }

}

