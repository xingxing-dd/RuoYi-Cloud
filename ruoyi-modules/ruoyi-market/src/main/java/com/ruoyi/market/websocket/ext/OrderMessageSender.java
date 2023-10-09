package com.ruoyi.market.websocket.ext;

import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.websocket.MessageSender;
import org.springframework.stereotype.Component;

@Component("order")
public class OrderMessageSender implements MessageSender {

    @Override
    public void send(SocketIOSession session, String productCode) {

    }

}
