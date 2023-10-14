package com.ruoyi.market.websocket.ext;

import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.websocket.PriceFluctuationsMessageSender;
import org.springframework.stereotype.Component;

@Component("order")
public class TradeOrderMessageSender implements PriceFluctuationsMessageSender {

    @Override
    public void send(SocketIOSession session, String productCode) {

    }

}
