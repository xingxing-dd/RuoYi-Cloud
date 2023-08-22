package com.ruoyi.market.biz.core.helper;

import com.ruoyi.common.core.constant.MarketConstant;
import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.biz.core.model.ProductKLineCache;
import com.ruoyi.market.biz.core.model.ProductPriceCache;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.domain.ProductPrice;
import com.ruoyi.market.mapper.ProductInfoMapper;
import com.ruoyi.market.mapper.ProductPriceMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

@Component
public class ProductPriceHelper {

    @Resource
    private RedisService redisService;

    @Resource
    private ProductPriceMapper productPriceMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

    public List<ProductInfo> getValidProduct() {
        ProductInfo params = new ProductInfo();
        params.setStatus(MarketConstant.VALID);
        List<ProductInfo> productInfos = productInfoMapper.selectProductInfoList(params);
        if (CollectionUtils.isEmpty(productInfos)) {
            return null;
        }
        return productInfos;
    }

    public boolean doRefreshPrice(String productCode, BigDecimal currentPrice, Date currentTime) {
        if (currentPrice == null) {
            return false;
        }
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, DateUtils.dateTime());
        ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
        if (productPriceCache != null && !productPriceCache.hasChange(currentTime, currentPrice)) {
            return false;
        }
        if (productPriceCache == null) {
            productPriceCache = new ProductPriceCache();
            productPriceCache.setProductCode(productCode);
            productPriceCache.setCurrentPrice(currentPrice);
            productPriceCache.setOpenPrice(currentPrice);
            productPriceCache.setRange(BigDecimal.ZERO);
        }
        productPriceCache.setCurrentTime(currentTime);
        redisService.setCacheObject(productPriceCacheKey, productPriceCache);
        //productPriceMapper.insertProductPrice(productPrice);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_1M);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_5M);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_15M);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_30M);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_1H);
        doRefreshPrice(productPriceCache, MarketPriceTypeEnum.MK_1D);
        return true;
    }

    private void doRefreshPrice(ProductPriceCache productPriceCache, MarketPriceTypeEnum priceType) {
        String productPriceKey = String.format(PRODUCT_PRICE_INFO_KEY, productPriceCache.getProductCode(), priceType.getKey());
        List<ProductKLineCache> productPrices = redisService.getCacheList(productPriceKey);
        if (CollectionUtils.isEmpty(productPrices)) {
            ProductKLineCache productPrice = new ProductKLineCache();
            productPrice.setProductCode(productPriceCache.getProductCode());
            productPrice.setTimestamp(productPriceCache.getCurrentTime().getTime());
            productPrice.setHigh(productPriceCache.getCurrentPrice());
            productPrice.setLow(productPriceCache.getCurrentPrice());
            productPrice.setOpen(productPriceCache.getCurrentPrice());
            productPrice.setClose(productPriceCache.getCurrentPrice());
            productPrices = Collections.singletonList(productPrice);
        }
        ProductKLineCache productPrice = productPrices.get(productPrices.size() - 1);
        if (productPriceCache.getCurrentTime().getTime() - productPrice.getTimestamp() > priceType.getInterval()) {
            ProductKLineCache newestProductPrice = getProductPrice(productPriceCache, productPrice);
            productPrices.add(newestProductPrice);
        } else {
            productPrice.setHigh(productPrice.getClose().compareTo(productPriceCache.getCurrentPrice()) >= 0 ? productPrice.getClose() : productPriceCache.getCurrentPrice());
            productPrice.setLow(productPrice.getClose().compareTo(productPriceCache.getCurrentPrice()) <= 0 ? productPrice.getClose() : productPriceCache.getCurrentPrice());
            productPrice.setClose(productPriceCache.getCurrentPrice());
        }
        if (productPrices.size() > 60) {
            productPrices.remove(0);
        }
        redisService.setCacheList(productPriceKey, productPrices);
        redisService.expire(productPriceKey, 1, TimeUnit.DAYS);
    }

    private ProductKLineCache getProductPrice(ProductPriceCache priceCache, ProductKLineCache originproductPrice) {
        ProductKLineCache productPrice = new ProductKLineCache();
        productPrice.setProductCode(priceCache.getProductCode());
        productPrice.setTimestamp(priceCache.getCurrentTime().getTime());
        productPrice.setHigh(originproductPrice.getClose().compareTo(priceCache.getCurrentPrice()) >= 0 ? originproductPrice.getClose() : priceCache.getCurrentPrice());
        productPrice.setLow(originproductPrice.getClose().compareTo(priceCache.getCurrentPrice()) <= 0 ? originproductPrice.getClose() : priceCache.getCurrentPrice());
        productPrice.setOpen(originproductPrice.getClose());
        productPrice.setClose(priceCache.getCurrentPrice());
        return productPrice;
    }

}
