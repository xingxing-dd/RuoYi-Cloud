package com.ruoyi.market.websocket;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

public abstract class AbstractPriceFluctuationsMessageSender<T> implements PriceFluctuationsMessageSender {

    @Autowired
    private RedisService redisService;

    @Override
    public void send(SocketIOSession session, String productCode) {
        if (StringUtils.isBlank(session.getTopic()) || StringUtils.isBlank(session.getInterval()) || !filterMessage(session, productCode)) {
            return;
        }
        String productPriceKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, session.getInterval());
        ProductKLineCache productPrice = redisService.getLastObject(productPriceKey, ProductKLineCache.class);
        if (productPrice == null) {
            return;
        }
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, DateUtils.dateTime());
        ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
        if (productPriceCache == null) {
            return;
        }
        session.getClient().sendEvent(getMessageType(), buildMessage(productPrice, productPriceCache));
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
     * 构建消息
     * @param productPrice
     */
    protected abstract T buildMessage(ProductKLineCache productPrice, ProductPriceCache productPriceCache);

    protected abstract String getMessageType();

}
