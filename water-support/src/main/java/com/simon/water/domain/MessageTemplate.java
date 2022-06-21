package com.simon.water.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * creat by 郎亚坤
 * 2022/6/20 21:04
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplate {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 模板标题
     */
    private String name;

    /**
     * 审核状态
     * 0.待审核 20.审核成功 30.被拒绝
     */
    private Integer auditStatus;

    /**
     * 工单ID（审核模板走工单）
     */
    private String flowId;

    /**
     * 消息状态
     * 10.新建 20.停用 30.启用 40.等待发送 50.发送中 60.发送成功 70.发送失败
     */
    private Integer msgStatus;

    /**
     * 发送的Id类型
     * 消息的发送ID类型：10. userId 20.did 30.手机号 40.openId 50.email
     */
    private Integer idType;

    /**
     * 发送渠道
     * 消息发送渠道：10.IM 20.Push 30.短信 40.Email 50.公众号 60.小程序
     */
    private Integer sendChannel;

    /**
     * 模板类型
     * 10.运营类 20.技术类接口调用
     */
    private Integer templateType;

    /**
     * 消息类型
     * 10.通知类消息 20.营销类消息 30.验证码类消息
     */
    private Integer msgType;

    /**
     * 推送消息的时间
     *期望发送时间：立即发送.10 定时任务以及周期任务.cron表达式
     */
    private String expectPushTime;

    /**
     * 消息内容  {$var} 为占位符
     */
    private String msgContent;

    /**
     * 发送账号（邮件下可有多个发送账号、短信可有多个发送账号..）
     */
    private Integer sendAccount;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String updator;

    /**
     * 审核者
     */
    private String auditor;

    /**
     * 业务方团队
     */
    private String team;

    /**
     * 业务方
     */
    private String proposer;

    /**
     * 是否删除
     * 0：不删除
     * 1：删除
     */
    private Integer isDeleted;

    /**
     * 创建时间 单位 s
     */
    private Integer created;

    /**
     * 更新时间 单位s
     */
    private Integer updated;

    /**
     * 消息去重时间 单位小时
     */
    private Integer deduplicationTime;

    /**
     * 是否夜间屏蔽
     * 0:不屏蔽
     * 1：屏蔽
     */
    private Integer isNightShield;

}

