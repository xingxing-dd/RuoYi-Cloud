package com.ruoyi.market.websocket;

import com.ruoyi.common.websocket.session.model.SocketIOSession;
import org.springframework.scheduling.annotation.Async;

public interface PriceFluctuationsMessageSender {

    @Async
    void send(SocketIOSession session, String productCode);

}
