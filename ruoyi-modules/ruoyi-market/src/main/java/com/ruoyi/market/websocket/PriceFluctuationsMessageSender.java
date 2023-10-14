package com.ruoyi.market.websocket;

import com.ruoyi.common.websocket.session.model.SocketIOSession;

public interface PriceFluctuationsMessageSender {

    void send(SocketIOSession session, String productCode);

}
