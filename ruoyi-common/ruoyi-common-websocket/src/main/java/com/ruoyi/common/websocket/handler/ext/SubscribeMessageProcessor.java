package com.ruoyi.common.websocket.handler.ext;

import com.ruoyi.common.websocket.handler.MessageProcessor;
import com.ruoyi.common.websocket.message.Message;
import com.ruoyi.common.websocket.session.ClientSessionPool;
import com.ruoyi.common.websocket.session.model.ClientSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("subscribeMessageProcessor")
public class SubscribeMessageProcessor implements MessageProcessor<String, String> {

    private final ClientSessionPool clientSessionPool;

    @Override
    public Message<String> process(String token, Message<String> message) {
        ClientSession clientSession = clientSessionPool.getSession(token);
        if (clientSession == null || clientSession.isDeath()) {
            return Message.<String>builder().build();
        }
        clientSession.setTopic(message.getBody());
        return Message.<String>builder().build();
    }

}
