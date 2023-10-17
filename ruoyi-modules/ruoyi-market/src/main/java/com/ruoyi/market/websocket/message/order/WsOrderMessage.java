package com.ruoyi.market.websocket.message.order;

import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.market.websocket.message.order.model.TradeOrderDetail;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class WsOrderMessage {

    private BigDecimal totalIncome;

    private BigDecimal totalMargin;

    private BigDecimal riskRate;

    private List<TradeOrderDetail> tradeOrders;

}
