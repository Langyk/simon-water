package com.simon.water.serviceimpl.config;

import com.simon.water.pipeline.BusinessProcess;
import com.simon.water.pipeline.ProcessController;
import com.simon.water.pipeline.ProcessTemplate;
import com.simon.water.serviceapi.enums.BusinessCode;
import com.simon.water.serviceimpl.action.AfterParamCheckAction;
import com.simon.water.serviceimpl.action.AssembleAction;
import com.simon.water.serviceimpl.action.PreParamCheckAction;
import com.simon.water.serviceimpl.action.SendMqAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/7/1 16:47
 * api层的pipline配置类
 */

@Configuration
public class PipelineConfig {
    /**
     * 普通发送执行流程
     * 1. 参数前置校验
     * 2. 组装参数
     * 3. 参数后置校验
     * 4. 发送消息至MQ
     * @return
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate(){
        ProcessTemplate processTemplate = new ProcessTemplate();
        ArrayList<BusinessProcess> processList = new ArrayList<>();
        /** 四个任务处理流程 */
        processList.add(preParamCheckAction());
        processList.add(assembleAction());
        processList.add(afterParamCheckAction());
        processList.add(sendMqAction());

        processTemplate.setProcessList(processList);
        return processTemplate;
    }


    /**
     * pipeline流程控制器
     * 目前暂定只有 普通发送的流程
     * 后续扩展则加BusinessCode和ProcessTemplate
     * @return
     */
    @Bean
    public ProcessController processController(){
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>();
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }

    /**
     * 组装参数Action
     * @return
     */
    @Bean
    public AssembleAction assembleAction(){
        return new AssembleAction();
    }


    /**
     * 参数前置校验Action
     * @return
     */
    @Bean
    public PreParamCheckAction preParamCheckAction(){
        return new PreParamCheckAction();
    }

    /**
     * 参数的后置校验Action
     * @return
     */
    @Bean
    public AfterParamCheckAction afterParamCheckAction(){
        return new AfterParamCheckAction();
    }

    /**
     * 发送消息至MQ的Action
     * @return
     */
    @Bean
    public SendMqAction sendMqAction(){
        return new SendMqAction();
    }

}

