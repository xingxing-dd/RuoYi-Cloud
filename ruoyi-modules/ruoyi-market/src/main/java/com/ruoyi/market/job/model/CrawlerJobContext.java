package com.ruoyi.market.job.model;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.market.domain.ProductPrice;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

import static com.ruoyi.common.core.constant.MarketConstant.DEFAULT_TIME_FORMAT;

@Data
@ToString
public class CrawlerJobContext {

    private String currentPrice;

    private ProductPrice productPrice;

    public void doProcessPrice(String newestPrice) {
        if (StringUtils.equals(currentPrice, newestPrice)) {
            return;
        }
        String currentTime = DateUtils.dateTimeNow(DEFAULT_TIME_FORMAT);
        BigDecimal newestPriceValue = new BigDecimal(newestPrice);
        if (productPrice == null) {
            productPrice = new ProductPrice();
            productPrice.setTimestamp(DateUtils.dateTime(currentTime, DEFAULT_TIME_FORMAT));
            productPrice.setHigh(newestPriceValue);
            productPrice.setLow(newestPriceValue);
            productPrice.setOpen(newestPriceValue);
            productPrice.setClose(newestPriceValue);
        }
        if (newestPriceValue.compareTo(productPrice.getHigh()) > 0) {
            productPrice.setHigh(newestPriceValue);
        }
        if (newestPriceValue.compareTo(productPrice.getLow()) < 0) {
            productPrice.setLow(newestPriceValue);
        }
    }

}
