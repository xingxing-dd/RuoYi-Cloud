package com.ruoyi.client.factory;

import com.ruoyi.client.RemoteClientUserService;
import com.ruoyi.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteClientUserFallbackFactory  implements FallbackFactory<RemoteClientUserService> {

    private final static Logger log = LoggerFactory.getLogger(RemoteClientUserFallbackFactory.class);

    @Override
    public RemoteClientUserService create(Throwable cause) {
        log.error("用户服务调用失败:{}", cause.getMessage());
        return new RemoteClientUserService()
        {
        };
    }

}
