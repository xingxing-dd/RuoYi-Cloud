package com.ruoyi.market.websocket.message;

import com.ruoyi.client.domain.TradeOrder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class WsOrderMessage {

    private List<TradeOrder> tradeOrders;

}
