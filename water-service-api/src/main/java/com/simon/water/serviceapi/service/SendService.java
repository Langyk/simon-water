package com.simon.water.serviceapi.service;


import com.simon.water.serviceapi.domain.BatchSendRequest;
import com.simon.water.serviceapi.domain.SendRequest;
import com.simon.water.serviceapi.domain.SendResponse;

/**
 * 发送接口（单次和批量）
 */
public interface SendService {

    /**
     * 单模板单文案发送接口
     * @param sendRequest 发送参数
     * @return
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 单模板多文案发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
