package com.ruoyi.client.job;

import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.ITradeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientIncomeJob {

    @Autowired
    private ITradeOrderService tradeOrderService;

    public void process() {
        log.info("开始处理收益统计");
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setStatus(0L);

        log.info("处理收益统计结束");
    }

}
