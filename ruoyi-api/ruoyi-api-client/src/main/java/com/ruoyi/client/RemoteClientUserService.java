package com.ruoyi.client;

import com.ruoyi.client.factory.RemoteClientUserFallbackFactory;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "remoteClientUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteClientUserFallbackFactory.class)

public interface RemoteClientUserService {



}
