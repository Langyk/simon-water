package com.simon.water.serviceapi.service;


import com.simon.water.serviceapi.domain.SendRequest;
import com.simon.water.serviceapi.domain.SendResponse;

/**
 * 发送接口
 */
public interface SendService {

    SendResponse send(SendRequest sendRequest);

    SendResponse batchSend(SendRequest sendRequest);

}
