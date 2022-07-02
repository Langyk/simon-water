package com.simon.water.pipeline;

public interface BusinessProcess {
    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext context);
}
