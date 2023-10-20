package com.ruoyi.market.websocket.ext;

import com.ruoyi.client.RemoteClientOrderService;
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.websocket.AbstractPriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.message.order.WsOrderMessage;
import com.ruoyi.market.websocket.message.order.model.TradeOrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.common.core.constant.MarketConstant.*;

@Slf4j
@Component(WS_ORDER_MESSAGE_QUEUE)
public class TradeOrderMessageSender extends AbstractPriceFluctuationsMessageSender<WsOrderMessage> {

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteClientOrderService remoteClientOrderService;

    @Override
    protected boolean filterMessage(SocketIOSession session, String productCode) {
        if (redisService.hasKey("ruoyi-market:notify:time:" + DateUtils.dateTimeNow())) {
            return false;
        }
        redisService.setCacheObject("ruoyi-market:notify:time:" + DateUtils.dateTimeNow(), true, 5L, TimeUnit.SECONDS);
        return session.getUserId() != null;
    }

    @Override
    protected void sendMessage(SocketIOSession session,String productCode) {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setUserId(session.getUserId());
        tradeOrder.setStatus(Long.valueOf(session.getParam(REGISTER_ORDER_STATUS_KEY)));
        R<List<TradeOrder>> result = remoteClientOrderService.orderList(tradeOrder);
        if (R.isError(result)) {
            return;
        }
        List<TradeOrder> tradeOrders = result.getData();
        if (CollectionUtils.isEmpty(tradeOrders)) {
            return;
        }
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalMargin = BigDecimal.ZERO;
        List<TradeOrderDetail> tradeOrderDetails = new ArrayList<>();
        for (TradeOrder order: tradeOrders) {
            String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, order.getProductCode(), DateUtils.dateTime());
            ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
            if (productPriceCache == null) {
                continue;
            }
            TradeOrderDetail tradeOrderDetail = getTradeOrderDetail(productPriceCache, order);
            totalIncome = totalIncome.add(order.getIncome());
            totalMargin =  totalMargin.add(order.getMargin());
            tradeOrderDetails.add(tradeOrderDetail);
        }
        WsOrderMessage wsOrderMessage = new WsOrderMessage();
        wsOrderMessage.setTotalIncome(totalIncome);
        wsOrderMessage.setTotalMargin(totalMargin);
        wsOrderMessage.setRiskRate(totalIncome.add(totalMargin).multiply(new BigDecimal(100)).divide(totalMargin, 2, RoundingMode.HALF_UP));
        wsOrderMessage.setTradeOrders(tradeOrderDetails);
        session.getClient().sendEvent(WS_ORDER_MESSAGE_QUEUE, wsOrderMessage);
    }

    private TradeOrderDetail getTradeOrderDetail(ProductPriceCache productPriceCache, TradeOrder order) {
        TradeOrderDetail tradeOrderDetail = new TradeOrderDetail();
        tradeOrderDetail.setOrderId(order.getOrderId().substring(0, 10));
        tradeOrderDetail.setProductCode(order.getProductCode());
        tradeOrderDetail.setTradePrice(order.getTradePrice());
        tradeOrderDetail.setCurrentPrice(productPriceCache.getCurrentPrice());
        tradeOrderDetail.setTradeDirect(order.getTradeDirect());
        tradeOrderDetail.setIncome(order.getIncome());
        tradeOrderDetail.setSheetNum(order.getSheetNum());
        tradeOrderDetail.setCreateTime(order.getCreateTime());
        return tradeOrderDetail;
    }

}
