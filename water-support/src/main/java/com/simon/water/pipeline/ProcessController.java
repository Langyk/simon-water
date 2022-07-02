package com.simon.water.pipeline;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.simon.water.common.enums.RespStatusEnum;
import com.simon.water.common.vo.BasicResultVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/7/1 14:40
 * 流程控制器
 */

@Slf4j
@Data
public class ProcessController {
    /**
     * 模板映射
     */
    private Map<String, ProcessTemplate> templateConfig = null;

    public ProcessContext process(ProcessContext context) {

        /**
         * 前置检查
         */
        if (!preCheck(context)) {
            return context;
        }

        /**
         * 遍历流程节点
         * 使用配置的方式注入对象
         */
        List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if (context.getNeedBreak()) {
                break;
            }
        }
        return context;

    }

        private Boolean preCheck(ProcessContext context){
            // 上下文
            if (context == null) {
                context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
                return false;
            }

            //业务代码
            String businessCode = context.getCode();
            if (StrUtil.isBlank(businessCode)) {
                context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
                return false;
            }

            // 执行模板
            ProcessTemplate processTemplate = templateConfig.get(businessCode);
            if (processTemplate == null) {
                context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL));
                return false;
            }

            // 执行模板列表
            List<BusinessProcess> processList = processTemplate.getProcessList();
            if (CollUtil.isEmpty(processList)) {
                context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_LIST_IS_NULL));
                return false;
            }
            return true;
        }

}

