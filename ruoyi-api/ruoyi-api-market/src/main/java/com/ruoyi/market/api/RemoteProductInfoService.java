package com.ruoyi.market.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.market.api.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.api.req.FinancialInfoReq;
import com.ruoyi.market.api.resp.ExchangeOrderCalculateVo;
import com.ruoyi.market.api.resp.FinancialInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(contextId = "remoteProductInfoService", value = ServiceNameConstants.MARKET_SERVICE)
public interface RemoteProductInfoService {

    @RequestMapping("/client/exchange/calculate")
    public R<ExchangeOrderCalculateVo> calculate(@RequestBody ExchangeOrderCalculateReq req);

    @PostMapping("/client/financial/queryProduct")
    public R<FinancialInfoResp> queryProduct(@RequestBody FinancialInfoReq req);

}
