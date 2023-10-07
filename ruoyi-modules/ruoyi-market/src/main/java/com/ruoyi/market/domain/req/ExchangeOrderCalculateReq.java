package com.ruoyi.market.domain.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ExchangeOrderCalculateReq implements Serializable {

    private String productCode;

    private String direct;

    private BigDecimal exchangePrice;

    private BigDecimal multiplier;

    private BigDecimal sheetNum;

}
