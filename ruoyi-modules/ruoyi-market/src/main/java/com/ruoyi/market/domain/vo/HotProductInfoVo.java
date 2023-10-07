package com.ruoyi.market.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class HotProductInfoVo implements Serializable {

    private String productCode;

    private String productName;

    private String productIcon;

    private BigDecimal currentPrice;

}
