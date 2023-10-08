package com.ruoyi.market.api.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ExchangeOrderCalculateVo implements Serializable {

    private BigDecimal feeAmount;

    private BigDecimal margin;

    private BigDecimal exchangeAmount;

    private BigDecimal totalBalance;

}
