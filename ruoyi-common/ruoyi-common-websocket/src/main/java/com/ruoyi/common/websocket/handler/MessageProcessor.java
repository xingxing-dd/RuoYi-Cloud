package com.ruoyi.common.websocket.handler;

import com.ruoyi.common.websocket.message.Message;

public interface MessageProcessor<IN, OUT> {

    Message<OUT> process(String token, Message<IN> message);

}
