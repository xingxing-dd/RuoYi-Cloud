package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class WithdrawCalculateResp {

    private BigDecimal feeAmount;

    private BigDecimal receivedAmount;

}
