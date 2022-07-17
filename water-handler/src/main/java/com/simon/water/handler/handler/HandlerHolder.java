package com.simon.water.handler.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * creat by 郎亚坤
 * 2022/7/16 11:33
 *
 * Channel -> Handler 的映射关系
 */

@Component
public class HandlerHolder {

    private Map<Integer,Handler> handlers = new HashMap<Integer,Handler>(12);

    public void putHandler(Integer channelCode, Handler handler){
        handlers.put(channelCode,handler);
    }

    public Handler route(Integer channelCode){
        return handlers.get(channelCode);
    }


}

