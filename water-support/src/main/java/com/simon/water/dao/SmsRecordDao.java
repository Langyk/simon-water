package com.simon.water.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simon.water.domain.SmsRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * creat by 郎亚坤
 * 2022/6/20 20:11
 */


@Mapper
public interface SmsRecordDao extends BaseMapper<SmsRecord> {
}
