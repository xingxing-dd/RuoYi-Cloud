package com.ruoyi.client.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class RechargeOrderVo implements Serializable {

    /** 充值方式 */
    private String rechargeType;

    /** 到账金额 */
    private BigDecimal receiveAmount;

    /** 到账币种 */
    private String receiveCurrency;

    private String status;

    private Date createAt;

}
