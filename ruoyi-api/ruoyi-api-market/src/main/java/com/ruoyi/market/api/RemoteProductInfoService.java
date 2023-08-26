package com.ruoyi.market.api;

import com.ruoyi.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.MARKET_SERVICE)
public interface RemoteProductInfoService {
}
