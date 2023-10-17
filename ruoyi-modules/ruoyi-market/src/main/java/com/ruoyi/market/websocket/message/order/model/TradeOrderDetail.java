package com.ruoyi.market.websocket.message.order.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class TradeOrderDetail {

    private String orderId;

    private String productCode;

    private BigDecimal tradePrice;

    private BigDecimal currentPrice;

    private String tradeDirect;

    private BigDecimal income;

    private BigDecimal sheetNum;

    private Date createTime;

}
