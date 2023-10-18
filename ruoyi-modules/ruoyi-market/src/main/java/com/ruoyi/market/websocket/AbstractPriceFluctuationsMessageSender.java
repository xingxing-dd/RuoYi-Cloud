package com.ruoyi.market.websocket;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;
import static com.ruoyi.common.core.constant.MarketConstant.REGISTER_INTERVAL_KEY;

public abstract class AbstractPriceFluctuationsMessageSender<T> implements PriceFluctuationsMessageSender {

    @Autowired
    protected RedisService redisService;

    @Override
    public void send(SocketIOSession session, String productCode) {
        if (StringUtils.isBlank(session.getType()) || !filterMessage(session, productCode)) {
            return;
        }
        sendMessage(session, productCode);
    }

    /**
     * 过滤消息
     * @param session
     * @param productCode
     * @return
     */
    protected boolean filterMessage(SocketIOSession session, String productCode) {
        return false;
    }

    protected void getPriceMarkUp() {

    }

    /**
     * 发送消息
     * @param session
     */
    protected abstract void sendMessage(SocketIOSession session, String productCpde);

}
