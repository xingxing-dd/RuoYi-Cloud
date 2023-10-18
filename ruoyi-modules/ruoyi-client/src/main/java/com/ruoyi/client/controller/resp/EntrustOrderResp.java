package com.ruoyi.client.controller.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class EntrustOrderResp {

    /** 订单号 */
    private String orderId;

    /** 产品编号 */
    private String productCode;

    /** 倍率 */
    private BigDecimal multiplier;

    /** 保证金 */
    private BigDecimal margin;

    /** 张数 */
    private BigDecimal sheetNum;

    /** 交易方向 */
    private String tradeDirect;

    /** 交易价格 */
    private BigDecimal tradePrice;

    private BigDecimal currentPrice;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal feeAmount;

    /** 状态(委托中/已成交，已取消) */
    @Excel(name = "状态(委托中/已成交，已取消)")
    private Long status;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completedTime;
}
