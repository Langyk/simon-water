package com.simon.water.serviceimpl.pipeline;

/**
 * 责任链上下文
 * creat by 郎亚坤
 * 2022/6/26 13:09
 */
public class ProcessContext {

    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 存储责任链上下文数据的模型
     */
    private ProcessModel processModel;


    /**
     * 责任链中断的标识
     */
    private Boolean needBreak = false;

}

