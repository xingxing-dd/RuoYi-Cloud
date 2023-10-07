package com.ruoyi.client.controller.req;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class WithdrawOrderSubmitReq {

    private Long fundAcctId;

    private BigDecimal amount;

    private String currency;

    private String remark;

}
