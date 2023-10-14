package com.ruoyi.market.websocket.ext;

import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.websocket.AbstractPriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.PriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.message.WsOrderMessage;
import org.springframework.stereotype.Component;

@Component("order")
public class TradeOrderMessageSender extends AbstractPriceFluctuationsMessageSender<WsOrderMessage> {

    @Override
    protected WsOrderMessage buildMessage(ProductKLineCache productPrice, ProductPriceCache productPriceCache) {
        return null;
    }

    @Override
    protected String getMessageType() {
        return null;
    }

}
