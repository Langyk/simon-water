package com.simon.water.serviceimpl.pipeline;

import java.util.List;

/**
 * 业务执行模板,把责任链的逻辑串起来
 * creat by 郎亚坤
 * 2022/6/26 13:16
 */
public class ProcessTemplate {

    private List<BussinessProcess> processList;

    public List<BussinessProcess> getProcessList(){
        return processList;
    }

    public void setProcessList(List<BussinessProcess> processList){
        this.processList = processList;
    }

}

