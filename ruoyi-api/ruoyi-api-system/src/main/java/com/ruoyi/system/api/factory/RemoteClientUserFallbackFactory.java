package com.ruoyi.system.api.factory;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.system.api.RemoteClientUserService;
import com.ruoyi.system.api.domain.ClientUser;
import com.ruoyi.system.api.model.LoginUser;
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
            @Override
            public R<LoginUser> getUserInfo(String username, String source) {
                return null;
            }

            @Override
            public R<Boolean> registerUserInfo(ClientUser clientUser, String source) {
                return null;
            }
        };
    }

}
