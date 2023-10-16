package com.ruoyi.market.websocket.ext;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.websocket.session.model.SocketIOSession;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.service.IProductInfoService;
import com.ruoyi.market.websocket.AbstractPriceFluctuationsMessageSender;
import com.ruoyi.market.websocket.message.WsPriceMessage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.MarketConstant.WS_PRICE_MESSAGE_QUEUE;

@Component("price")
public class DefaultPriceMessageSender extends AbstractPriceFluctuationsMessageSender<WsPriceMessage> {

    @Resource
    private IProductInfoService productInfoService;

    @Override
    protected boolean filterMessage(SocketIOSession session, String productCode) {
        if (StringUtils.equals(session.getTopic(), productCode)) {
            return true;
        }
        List<ProductInfo> productInfos = productInfoService.selectProductInfoByCategory(session.getTopic());
        if (CollectionUtils.isEmpty(productInfos)) {
            return false;
        }
        if (productInfos.stream().map(ProductInfo::getProductCode).collect(Collectors.toSet()).contains(productCode)) {
            return true;
        }
        return super.filterMessage(session, productCode);
    }

    @Override
    protected void sendMessage(SocketIOSession session, ProductKLineCache productPrice, ProductPriceCache productPriceCache) {
        WsPriceMessage message = new WsPriceMessage();
        message.setProductCode(productPriceCache.getProductCode());
        message.setCurrentPrice(productPriceCache.getCurrentPrice());
        message.setTimestamp(productPrice.getTimestamp());
        message.setOpen(productPrice.getOpen());
        message.setClose(productPrice.getClose());
        message.setLow(productPrice.getLow());
        message.setHigh(productPrice.getHigh());
        message.setRange(productPriceCache.getRange());
        session.getClient().sendEvent(WS_PRICE_MESSAGE_QUEUE, message);
    }

}
