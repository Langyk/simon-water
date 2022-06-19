package com.simon.water.common.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * creat by 郎亚坤
 * 2022/6/19 19:52
 */
    @Data
    @Builder
    public class SmsParam {

        /**
         * 需要发送的手机号
         */
        private Set<String> phones;

        /**
         * 发送文案
         */
        private String content;
    }


