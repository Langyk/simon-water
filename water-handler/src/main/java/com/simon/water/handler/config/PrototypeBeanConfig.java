package com.simon.water.handler.config;

import com.simon.water.handler.pending.Task;
import com.simon.water.handler.receiver.Receiver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * creat by 郎亚坤
 * 2022/7/16 11:14
 */

/**
 * Handler模块配置信息
 */
@Configuration
public class PrototypeBeanConfig {

    /**
     * 定义多例Receiver
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Receiver receiver(){
        return new Receiver();
    }

    /**
     * 定义多例的Task
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Task task() {
        return new Task();
    }
}

