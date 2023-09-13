package com.ruoyi.client.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class WithdrawOrderVo implements Serializable {

    @Excel(name = "订单号")
    private String orderId;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal amount;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 提现账号 */
    @Excel(name = "提现账号")
    private String fundAcct;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal feeAmount;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

}
