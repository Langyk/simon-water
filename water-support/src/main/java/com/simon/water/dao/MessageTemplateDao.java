package com.simon.water.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simon.water.domain.MessageTemplate;
import org.apache.ibatis.annotations.Mapper;


/**
 * 消息模板DAO
 */
@Mapper
public interface MessageTemplateDao extends BaseMapper<MessageTemplate> {
}
