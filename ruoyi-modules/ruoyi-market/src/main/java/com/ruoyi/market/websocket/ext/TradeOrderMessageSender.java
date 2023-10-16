package com.ruoyi.market.websocket.ext;

import com.ruoyi.client.RemoteClientOrderService;
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.websocket.AbstractPriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.PriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.message.WsOrderMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.ruoyi.common.core.constant.MarketConstant.WS_ORDER_MESSAGE_QUEUE;

@Component(WS_ORDER_MESSAGE_QUEUE)
public class TradeOrderMessageSender extends AbstractPriceFluctuationsMessageSender<WsOrderMessage> {

    private final static ThreadLocal<Long> localUser = new ThreadLocal<>();

    @Resource
    private RemoteClientOrderService remoteClientOrderService;

    @Override
    protected boolean filterMessage(SocketIOSession session, String productCode) {
        localUser.remove();
        if (session.getUserId() != null) {
            localUser.set(session.getUserId());
            return true;
        }
        return super.filterMessage(session, productCode);
    }

    @Override
    protected void sendMessage(SocketIOSession session, ProductKLineCache productPrice, ProductPriceCache productPriceCache) {
        if (localUser.get() == null) {
            return;
        }
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setUserId(localUser.get());
        tradeOrder.setStatus(Long.valueOf(session.getTopic()));
        R<List<TradeOrder>> result = remoteClientOrderService.orderList(tradeOrder);
        if (R.isError(result)) {
            return;
        }
        session.getClient().sendEvent(WS_ORDER_MESSAGE_QUEUE, result.getData());
    }

}
