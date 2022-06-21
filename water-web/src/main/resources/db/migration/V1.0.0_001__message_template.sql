
DROP TABLE IF EXISTS `message_template`;
CREATE TABLE `message_template`
(
    `id`                 bigint(20)                              NOT NULL AUTO_INCREMENT,
    `name`               varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
    `audit_status`       tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '当前消息审核状态： 10.待审核 20.审核成功 30.被拒绝',
    `flow_id`            varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '工单ID',
    `msg_status`         tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '当前消息状态：10.新建 20.停用 30.启用 40.等待发送 50.发送中 60.发送成功 70.发送失败',
    `id_type`            tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '消息的发送ID类型：10. userId 20.did 30.手机号 40.openId 50.email',
    `send_channel`       tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '消息发送渠道：10.IM 20.Push 30.短信 40.Email 50.公众号 60.小程序',
    `template_type`      tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '10.运营类 20.技术类接口调用',
    `msg_type`           tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '10.通知类消息 20.营销类消息 30.验证码类消息',
    `expect_push_time`   varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '期望发送时间：立即发送.10 定时任务以及周期任务.cron表达式',
    `msg_content`        varchar(600) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '消息内容 占位符用{$var}表示',
    `send_account`       tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '发送账号 一个渠道下可存在多个账号',
    `creator`            varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '创建者',
    `updator`            varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '更新者',
    `auditor`            varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '审核人',
    `team`               varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '业务方团队',
    `proposer`           varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '业务方',
    `is_deleted`         tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否删除：0.不删除 1.删除',
    `created`            int(11)                                 NOT NULL DEFAULT '0' COMMENT '创建时间',
    `updated`            int(11)                                 NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deduplication_time` tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '去重时间 单位小时',
    `is_night_shield`    tinyint(4)                              NOT NULL DEFAULT '0' COMMENT '是否夜间屏蔽：0.夜间不屏蔽 1.夜间屏蔽',
    PRIMARY KEY (`id`),
    KEY `idx_channel` (`send_channel`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息模板信息';