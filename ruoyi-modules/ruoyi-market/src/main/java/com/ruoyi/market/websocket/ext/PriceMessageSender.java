package com.ruoyi.market.websocket.ext;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.service.IProductInfoService;
import com.ruoyi.market.websocket.MessageSender;
import com.ruoyi.market.websocket.message.WsPriceMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

@Component("price")
public class PriceMessageSender implements MessageSender {

    @Resource
    private RedisService redisService;

    @Resource
    private IProductInfoService productInfoService;

    @Override
    public void send(SocketIOSession session, String productCode) {
        if (StringUtils.isBlank(session.getTopic()) || StringUtils.isBlank(session.getInterval())) {
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
        WsPriceMessage message = new WsPriceMessage();
        message.setProductCode(productCode);
        message.setCurrentPrice(productPriceCache.getCurrentPrice());
        message.setTimestamp(productPrice.getTimestamp());
        message.setOpen(productPrice.getOpen());
        message.setClose(productPrice.getClose());
        message.setLow(productPrice.getLow());
        message.setHigh(productPrice.getHigh());
        message.setRange(productPriceCache.getRange());
        if (session.getTopic().equals(productCode)) {
            session.getClient().sendEvent("message", message);
            return;
        }
    }

}
