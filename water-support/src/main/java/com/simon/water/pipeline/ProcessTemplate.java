package com.simon.water.pipeline;

import java.util.List;

/**
 * creat by 郎亚坤
 * 2022/7/1 14:48
 * 业务执行模板（把责任链的逻辑串起来）
 */
public class ProcessTemplate {
    /** 1.多个任务处理流程 */
    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }
    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}

