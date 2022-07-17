package com.simon.water.handler.config;

import cn.hutool.core.thread.ExecutorBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * creat by 郎亚坤
 * 2022/7/3 10:05
 *
 * 线程池配置信息
 */

public class ThreadPoolConfig {
    public static ExecutorService getThreadPool(Integer coreSize, Integer maxSize, Integer queueSiz) {
        ThreadPoolExecutor threadPoolExecutor = ExecutorBuilder.create()
                .setCorePoolSize(coreSize)
                .setMaxPoolSize(maxSize)
                .setKeepAliveTime(60)
                .setWorkQueue(new LinkedBlockingQueue<>(queueSiz))
                .setHandler(new ThreadPoolExecutor.CallerRunsPolicy())
                .build();

        return threadPoolExecutor;
    }


}

