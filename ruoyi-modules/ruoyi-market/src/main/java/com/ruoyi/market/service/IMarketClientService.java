package com.ruoyi.market.service;

import com.ruoyi.market.domain.ProductCategory;
import com.ruoyi.market.domain.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.domain.vo.ExchangeOrderCalculateVo;
import com.ruoyi.market.domain.vo.HotProductInfoVo;
import com.ruoyi.market.domain.vo.ProductConfigVo;
import com.ruoyi.market.domain.vo.ProductInfoVo;

import java.util.List;

public interface IMarketClientService {

    /**
     * category
     * @return
     */
    List<ProductCategory> selectProductCategoryList();

    /**
     * category product
     * @param category
     * @return
     */
    List<ProductInfoVo> selectCategoryProductPrices(String category);

    List<HotProductInfoVo> selectHotProducts();

    /**
     * 产品信息
     * @param productCode
     * @return
     */
    ProductInfoVo selectProductPrice(String productCode);


    ProductInfoVo selectProductPrice(String productCode, String priceType);

    /**
     * 查询产品配置
     * @param productCode
     * @return
     */
    ProductConfigVo selectProductConfig(String productCode);

    ExchangeOrderCalculateVo calculate(ExchangeOrderCalculateReq req);
}
