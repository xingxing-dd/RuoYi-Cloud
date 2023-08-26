package com.ruoyi.market.controller.client;

import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.domain.ProductCategory;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.domain.req.ProductQueryReq;
import com.ruoyi.market.domain.vo.IndexProductInfoVo;
import com.ruoyi.market.domain.vo.ProductPriceVo;
import com.ruoyi.market.service.IProductCategoryService;
import com.ruoyi.market.service.IProductInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;
import static com.ruoyi.common.core.constant.MarketConstant.VALID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketClientController {

    private final IProductCategoryService productCategoryService;

    private final IProductInfoService productInfoService;

    private final RedisService redisService;

    @PostMapping("/categories")
    public AjaxResult categories() {
        ProductCategory params = new ProductCategory();
        params.setStatus(VALID);
        List<ProductCategory> productCategories = productCategoryService.selectProductCategoryList(params);
        if (CollectionUtils.isEmpty(productCategories)) {
            return AjaxResult.success();
        }
        return AjaxResult.success(productCategories);
    }

    @PostMapping("/products")
    public AjaxResult products(@RequestBody ProductQueryReq req) {
        if (req == null || StringUtils.isBlank(req.getCategoryCode())) {
            return AjaxResult.success();
        }
        ProductInfo params = new ProductInfo();
        params.setCategoryCode(req.getCategoryCode());
        params.setStatus(VALID);
        List<ProductInfo> productInfos = productInfoService.selectProductInfoList(params);
        if (CollectionUtils.isEmpty(productInfos)) {
            return AjaxResult.success();
        }
        List<IndexProductInfoVo> productInfoVos = new ArrayList<>();
        for (ProductInfo productInfo: productInfos) {
            IndexProductInfoVo productInfoVo = new IndexProductInfoVo();
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
            String productPriceKey = String.format(PRODUCT_PRICE_INFO_KEY, productPriceCache.getProductCode(), MarketPriceTypeEnum.MK_1M.getKey());
            List<ProductKLineCache> productPriceCaches = redisService.getCacheList(productPriceKey);
            if (productPriceCaches != null && productPriceCaches.size() > 60) {
                productPriceCaches = productPriceCaches.subList(productPriceCaches.size() - 60, productPriceCaches.size());
            }
            if (!CollectionUtils.isEmpty(productInfos)) {
                assert productPriceCaches != null;
                productInfoVo.setPrices(productPriceCaches.stream().map(priceCache -> {
                    ProductPriceVo productPriceVo = new ProductPriceVo();
                    BeanUtils.copyBeanProp(productPriceVo, priceCache);
                    return productPriceVo;
                }).collect(Collectors.toList()));
            }
            productInfoVos.add(productInfoVo);
        }
        return AjaxResult.success(productInfoVos);
    }

}
