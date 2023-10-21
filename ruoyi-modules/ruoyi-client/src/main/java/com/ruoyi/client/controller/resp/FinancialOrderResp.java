package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class FinancialOrderResp {

    private BigDecimal amount;

    private BigDecimal totalIncome;

    private BigDecimal interestRate;

    private BigDecimal yesterdayIncome;

}
