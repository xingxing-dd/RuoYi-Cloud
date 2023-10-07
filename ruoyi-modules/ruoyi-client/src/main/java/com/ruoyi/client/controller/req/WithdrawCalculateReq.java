package com.ruoyi.client.controller.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class WithdrawCalculateReq implements Serializable {

    private BigDecimal amount;

    private Long fundAcctId;

}
