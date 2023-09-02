package com.ruoyi.market.crawler.core.helper;

import com.ruoyi.common.core.constant.MarketConstant;
import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.domain.ProductPrice;
import com.ruoyi.market.mapper.ProductInfoMapper;
import com.ruoyi.market.mapper.ProductPriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

@Slf4j
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
        //log.info("获取到最新产品价格缓存:{}", productPriceCache);
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
        productPriceCache.setCurrentPrice(currentPrice);
        productPriceCache.setRange(productPriceCache.getCurrentPrice().subtract(productPriceCache.getOpenPrice()).divide(productPriceCache.getOpenPrice(), 4, BigDecimal.ROUND_HALF_UP). multiply(new BigDecimal(100)));
        productPriceCache.setCurrentTime(currentTime);
        //log.info("价格发生波动，更新产品价格缓存信息：{}", productPriceCache);
        redisService.setCacheObject(productPriceCacheKey, productPriceCache);
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
        ProductKLineCache productPrice = redisService.popLastObject(productPriceKey);
        //log.info("获取到最近一次产品价格信息：{}", productPrice);
        if (productPrice == null) {
            productPrice = new ProductKLineCache();
            productPrice.setProductCode(productPriceCache.getProductCode());
            productPrice.setTimestamp(productPriceCache.getCurrentTime().getTime());
            productPrice.setHigh(productPriceCache.getCurrentPrice());
            productPrice.setLow(productPriceCache.getCurrentPrice());
            productPrice.setOpen(productPriceCache.getCurrentPrice());
            productPrice.setClose(productPriceCache.getCurrentPrice());
        }
        //log.info("时间差值：{}, 时间间隔:{}", productPriceCache.getCurrentTime().getTime() - productPrice.getTimestamp(), priceType.getInterval());
        if (productPriceCache.getCurrentTime().getTime() - productPrice.getTimestamp() >= priceType.getInterval()) {
            if (log.isDebugEnabled()) {
                log.debug("跨时间段，将数据重新塞回列表:{}", productPrice);
            }
            productPrice.setClose(productPriceCache.getCurrentPrice());
            productPrice.setHigh(productPrice.getHigh().compareTo(productPriceCache.getCurrentPrice()) >= 0 ? productPrice.getHigh() : productPriceCache.getCurrentPrice());
            productPrice.setLow(productPrice.getLow().compareTo(productPriceCache.getCurrentPrice()) <= 0 ? productPrice.getLow() : productPriceCache.getCurrentPrice());
            redisService.pushLastObject(productPriceKey, productPrice);
            if (priceType.getKey().equals(MarketPriceTypeEnum.MK_1M.getKey())) {
                ProductPrice model = new ProductPrice();
                BeanUtils.copyBeanProp(model, productPrice);
                model.setTimestamp(new Date(productPrice.getTimestamp()));
                model.setRange(productPrice.getClose().divide(productPrice.getOpen(), 2, RoundingMode.HALF_UP));
                productPriceMapper.insertProductPrice(model);
            }
            productPrice = getProductPrice(productPriceCache, productPrice);
        } else {
            productPrice.setHigh(productPrice.getHigh().compareTo(productPriceCache.getCurrentPrice()) >= 0 ? productPrice.getHigh() : productPriceCache.getCurrentPrice());
            productPrice.setLow(productPrice.getLow().compareTo(productPriceCache.getCurrentPrice()) <= 0 ? productPrice.getLow() : productPriceCache.getCurrentPrice());
            productPrice.setClose(productPriceCache.getCurrentPrice());
        }
        if (log.isDebugEnabled()) {
            log.debug("刷新产品价格信息:{}", productPrice);
        }
        redisService.pushLastObject(productPriceKey, productPrice);
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
