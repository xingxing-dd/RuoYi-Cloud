package com.ruoyi.market.websocket;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.websocket.message.WsPriceMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

@Component
public class WebSocketMessageSender {

    @Resource
    private RedisService redisService;

    public void send(SocketIOSession session, String productCode) {
        if (StringUtils.isBlank(session.getTopic())) {
            return;
        }
        String productPriceKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, MarketPriceTypeEnum.MK_1M.getKey());
        ProductKLineCache productPrice = redisService.getLastObject(productPriceKey, ProductKLineCache.class);
        if (productPrice == null) {
            return;
        }
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, DateUtils.dateTime());
        ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
        if (productPriceCache == null) {
            return;
        }
        WsPriceMessage message = new WsPriceMessage();
        message.setProductCode(productCode);
        message.setCurrentPrice(productPriceCache.getCurrentPrice());
        message.setTimestamp(productPrice.getTimestamp());
        message.setOpen(productPrice.getOpen());
        message.setClose(productPrice.getClose());
        message.setLow(productPrice.getLow());
        message.setHigh(productPrice.getHigh());
        message.setRange(productPriceCache.getRange());
        if (session.getTopic().equals("INDEX") || session.getTopic().equals(productCode)) {
            session.getClient().sendEvent("message", JSON.toJSONString(message));
        }
    }

}
