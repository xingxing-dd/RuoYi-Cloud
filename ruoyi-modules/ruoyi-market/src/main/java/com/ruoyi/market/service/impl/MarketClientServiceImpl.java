package com.ruoyi.market.service.impl;

import com.ruoyi.client.RemoteClientWalletService;
import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.common.core.constant.MarketConstant;
import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.domain.ProductCategory;
import com.ruoyi.market.domain.ProductConfig;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.domain.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.domain.vo.*;
import com.ruoyi.market.service.IMarketClientService;
import com.ruoyi.market.service.IProductCategoryService;
import com.ruoyi.market.service.IProductConfigService;
import com.ruoyi.market.service.IProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.MarketConstant.*;

@Slf4j
@Service
public class MarketClientServiceImpl implements IMarketClientService {

    @Resource
    private IProductCategoryService productCategoryService;

    @Resource
    private IProductInfoService productInfoService;

    @Resource
    private IProductConfigService iProductConfigService;

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteClientWalletService remoteClientWalletService;

    @Override
    public List<ProductCategory> selectProductCategoryList() {
        ProductCategory params = new ProductCategory();
        params.setStatus(VALID);
        List<ProductCategory> productCategories = productCategoryService.selectProductCategoryList(params);
        if (CollectionUtils.isEmpty(productCategories)) {
            return Collections.emptyList();
        }
        return productCategories;
    }

    @Override
    public List<ProductInfoVo> selectCategoryProductPrices(String category) {
        ProductInfo params = new ProductInfo();
        params.setCategoryCode(category);
        params.setStatus(VALID);
        List<ProductInfo> productInfos = productInfoService.selectProductInfoListOrderByPriority(params);
        if (CollectionUtils.isEmpty(productInfos)) {
            return Collections.emptyList();
        }
        List<ProductInfoVo> productInfoVos = new ArrayList<>();
        for (ProductInfo productInfo: productInfos) {
            productInfoVos.add(buildProductInfoVo(productInfo, MarketPriceTypeEnum.MK_1M.getKey()));
        }
        return productInfoVos;
    }

    @Override
    public List<HotProductInfoVo> selectHotProducts() {
        ProductInfo params = new ProductInfo();
        params.setStatus(VALID);
        params.setHot(HOT_PRODUCT);
        List<ProductInfo> productInfos = productInfoService.selectProductInfoListOrderByPriority(params);
        if (CollectionUtils.isEmpty(productInfos)) {
            return Collections.emptyList();
        }
        List<HotProductInfoVo> hotProductInfoVos = new ArrayList<>();
        for (ProductInfo productInfo: productInfos) {
            HotProductInfoVo hotProductInfoVo = new HotProductInfoVo();
            BeanUtils.copyProperties(productInfo, hotProductInfoVo);
            String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productInfo.getProductCode(), DateUtils.dateTime());
            ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
            if (productPriceCache != null) {
                hotProductInfoVo.setCurrentPrice(productPriceCache.getCurrentPrice());
            }
            hotProductInfoVos.add(hotProductInfoVo);
        }
        return hotProductInfoVos;
    }

    @Override
    public ProductInfoVo selectProductPrice(String productCode) {
        return selectProductPrice(productCode, MarketPriceTypeEnum.MK_1M.getKey());
    }

    @Override
    public ProductInfoVo selectProductPrice(String productCode, String priceType) {
        ProductInfo params = new ProductInfo();
        params.setProductCode(productCode);
        params.setStatus(VALID);
        List<ProductInfo> productInfos = productInfoService.selectProductInfoList(params);
        if (CollectionUtils.isEmpty(productInfos)) {
            return null;
        }
        return buildProductInfoVo(productInfos.get(0), priceType);
    }

    @Override
    public ProductConfigVo selectProductConfig(String productCode) {
        ProductConfig productConfig = new ProductConfig();
        productConfig.setProductCode(productCode);
        List<ProductConfig> productConfigs = iProductConfigService.selectProductConfigList(productConfig);
        if (CollectionUtils.isEmpty(productConfigs)) {
            return null;
        }
        ProductConfigVo productConfigVo = new ProductConfigVo();
        org.springframework.beans.BeanUtils.copyProperties(productConfigs.get(0), productConfigVo);
        return productConfigVo;
    }

    @Override
    public ExchangeOrderCalculateVo calculate(ExchangeOrderCalculateReq req) {
        ProductConfig selectParams = new ProductConfig();
        selectParams.setProductCode(req.getProductCode());
        List<ProductConfig> productConfigs = iProductConfigService.selectProductConfigList(selectParams);
        if (CollectionUtils.isEmpty(productConfigs)) {
            return null;
        }
        ProductConfig productConfig = productConfigs.get(0);
        log.info("获取到交易配置:{}", productConfig);
        if (StringUtils.equals(BUY, req.getDirect())) {
            req.setExchangePrice(req.getExchangePrice().subtract(productConfig.getSpreadRate()));
        } else if (StringUtils.equals(SELL, req.getDirect())) {
            req.setExchangePrice(req.getExchangePrice().add(productConfig.getSpreadRate()));
        }
        BigDecimal exchangeAmount = req.getExchangePrice().multiply(req.getSheetNum()).multiply(productConfig.getEachSheetNum());
        log.info("购买总价值为:{}", exchangeAmount);
        ExchangeOrderCalculateVo exchangeOrderCalculateVo = new ExchangeOrderCalculateVo();
        exchangeOrderCalculateVo.setExchangeAmount(exchangeAmount);
        exchangeOrderCalculateVo.setFeeAmount(exchangeAmount.multiply(productConfig.getFeeRate()).setScale(productConfig.getPriceUnit(), RoundingMode.HALF_UP));
        exchangeOrderCalculateVo.setMargin(exchangeAmount.divide(req.getMultiplier(), productConfig.getPriceUnit(), RoundingMode.HALF_UP));
        ClientUserWallet clientUserWalletReq = new ClientUserWallet();
        clientUserWalletReq.setUserId(SecurityContextHolder.getUserId());
        R<ClientUserWallet> response = remoteClientWalletService.getUserWallet(clientUserWalletReq);
        log.info("获取到用户钱包信息:{}", response.getData());
        exchangeOrderCalculateVo.setTotalBalance(response.getData().getTotalAmount());
        return exchangeOrderCalculateVo;
    }

    private ProductInfoVo buildProductInfoVo(ProductInfo productInfo, String priceTypeEnum) {
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productInfoVo.setProductCode(productInfo.getProductCode());
        productInfoVo.setProductName(productInfo.getProductName());
        productInfoVo.setProductIcon(productInfo.getProductIcon());
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productInfo.getProductCode(), DateUtils.dateTime());
        ProductPriceCache productPriceCache = redisService.getCacheObject(productPriceCacheKey);
        if (productPriceCache != null) {
            productInfoVo.setCurrentPrice(productPriceCache.getCurrentPrice());
            productInfoVo.setRange(productPriceCache.getRange());
        }
        assert productPriceCache != null;
        String productPriceKey = String.format(PRODUCT_PRICE_INFO_KEY, productPriceCache.getProductCode(), priceTypeEnum);
        List<ProductKLineCache> productPriceCaches = redisService.getCacheList(productPriceKey);
        if (productPriceCaches != null && productPriceCaches.size() > 60) {
            productPriceCaches = productPriceCaches.subList(productPriceCaches.size() - 60, productPriceCaches.size());
        }
        productInfoVo.setPrices(productPriceCaches.stream().map(priceCache -> {
            ProductPriceVo productPriceVo = new ProductPriceVo();
            BeanUtils.copyBeanProp(productPriceVo, priceCache);
            return productPriceVo;
        }).collect(Collectors.toList()));
        return productInfoVo;
    }

}
