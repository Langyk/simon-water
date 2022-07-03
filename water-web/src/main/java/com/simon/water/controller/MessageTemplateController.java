package com.simon.water.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.simon.water.dao.MessageTemplateDao;
import com.simon.water.domain.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * creat by 郎亚坤
 * 2022/6/20 21:10
 */

/**
 * 插入模板测试类
 */

@RestController
public class MessageTemplateController {

    @Autowired
    private MessageTemplateDao messageTemplateDao;
    /**
     * test insert
     */

    @GetMapping("/insert")
    public String insert() {

        MessageTemplate messageTemplate = MessageTemplate.builder()
                .name("test短信")
                .auditStatus(10)
                .flowId("yyyy")
                .msgStatus(10)
                .idType(10)
                .sendChannel(10)
                .templateType(10)
                .msgType(10)
                .expectPushTime("0")
                .msgContent("3333333m")
                .sendAccount(66)
                .creator("yyyyc")
                .updator("yyyyu")
                .team("yyyt")
                .proposer("yyyy22")
                .auditor("yyyyyyz")
                .isDeleted(0)
                .created(Math.toIntExact(DateUtil.currentSeconds()))
                .updated(Math.toIntExact(DateUtil.currentSeconds()))
//                .deduplicationTime(1)
//                .isNightShield(0)
                .build();
        Integer result = messageTemplateDao.insert(messageTemplate);

        return JSON.toJSONString(result);
    }

    /**
     * test query
     */
    @GetMapping("/query")
    public String query() {
        LambdaQueryWrapper<MessageTemplate> lambdaQueryWrapper = new LambdaQueryWrapper<MessageTemplate>();

        Iterable<MessageTemplate> all = messageTemplateDao.selectList(lambdaQueryWrapper);
        for (MessageTemplate messageTemplate : all) {
            return JSON.toJSONString(messageTemplate);
        }
        return null;
    }
}

