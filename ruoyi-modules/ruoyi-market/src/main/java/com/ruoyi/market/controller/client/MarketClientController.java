package com.ruoyi.market.controller.client;

import com.ruoyi.common.core.constant.MarketPriceTypeEnum;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.crawler.core.model.ProductKLineCache;
import com.ruoyi.market.crawler.core.model.ProductPriceCache;
import com.ruoyi.market.domain.FinancialInfo;
import com.ruoyi.market.domain.ProductCategory;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.domain.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.domain.req.KLineQueryReq;
import com.ruoyi.market.domain.req.ProductQueryReq;
import com.ruoyi.market.domain.vo.ProductInfoVo;
import com.ruoyi.market.domain.vo.ProductPriceVo;
import com.ruoyi.market.service.IFinancialInfoService;
import com.ruoyi.market.service.IMarketClientService;
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
@RequestMapping("/client")
public class MarketClientController {

    private final IMarketClientService marketClientService;

    private final IFinancialInfoService financialInfoService;

    @PostMapping("/categories")
    public AjaxResult categories() {
        List<ProductCategory> productCategories = marketClientService.selectProductCategoryList();
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
        return AjaxResult.success(marketClientService.selectCategoryProductPrices(req.getCategoryCode()));
    }

    @PostMapping("/hot/products")
    public AjaxResult hotProducts() {
        return AjaxResult.success(marketClientService.selectHotProducts());
    }

    @PostMapping("/product")
    public AjaxResult product(@RequestBody ProductQueryReq req) {
        if (req == null || StringUtils.isBlank(req.getCategoryCode())) {
            return AjaxResult.success();
        }
        return AjaxResult.success(marketClientService.selectProductPrice(req.getProductCode()));
    }

    @PostMapping("/k-line")
    public AjaxResult productPriceDetail(@RequestBody KLineQueryReq req) {
        if (req == null || StringUtils.isAllBlank(req.getProductCode(), req.getType())) {
            return AjaxResult.error("params is null!");
        }
        if (MarketPriceTypeEnum.getPriceType(req.getType()) == null) {
            return AjaxResult.error("params is error!");
        }
        return AjaxResult.success(marketClientService.selectProductPrice(req.getProductCode(), req.getType()));
    }

    @PostMapping("/product/config")
    public AjaxResult productConfig(@RequestBody ProductQueryReq req) {
        return AjaxResult.success(marketClientService.selectProductConfig(req.getProductCode()));
    }

    @PostMapping("/exchange/calculate")
    public AjaxResult calculate(@RequestBody ExchangeOrderCalculateReq req) {
        return AjaxResult.success(marketClientService.calculate(req));
    }

    @PostMapping("/financial/queryProduct")
    public AjaxResult queryProduct(@RequestBody FinancialInfo financialInfo) {
        List<FinancialInfo> financialInfos = financialInfoService.selectFinancialInfoList(financialInfo);
        if (CollectionUtils.isEmpty(financialInfos)) {
            return AjaxResult.success();
        }
        return AjaxResult.success(financialInfos.get(0));
    }

}
