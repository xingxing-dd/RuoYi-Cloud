package com.ruoyi.market.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ExchangeOrderCalculateVo implements Serializable {

    private BigDecimal feeAmount;

    private BigDecimal margin;

    private BigDecimal totalBalance;

}
