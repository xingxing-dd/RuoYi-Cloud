package com.ruoyi.client;

import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "remoteClientOrderService", value = ServiceNameConstants.CLIENT_SERVICE)
public interface RemoteClientOrderService {

    @PostMapping("/trade/orderPriceChange")
    R<Void> orderPriceChange(@RequestParam("productCode") String productCode);

    @PostMapping("/trade/orderList")
    R<List<TradeOrder>> orderList(@RequestBody TradeOrder tradeOrder);
}
