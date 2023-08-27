package com.ruoyi.market.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class ProductInfoVo {

    /**
     * 产品code
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 图标
     */
    private String productIcon;

    /**
     * 当前价格
     */
    private BigDecimal currentPrice;

    /**
     * 涨跌幅
     */
    private BigDecimal range;

    /**
     * 价格图
     */
    private List<ProductPriceVo> prices;

}
