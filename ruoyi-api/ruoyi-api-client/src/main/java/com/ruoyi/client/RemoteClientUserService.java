package com.ruoyi.client;

import com.ruoyi.client.domain.ClientUser;
import com.ruoyi.client.factory.RemoteClientUserFallbackFactory;
import com.ruoyi.client.model.LoginUser;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "remoteClientUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteClientUserFallbackFactory.class)
public interface RemoteClientUserService {

    @GetMapping("/client/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param clientUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/client/user/register")
    public R<Boolean> registerUserInfo(@RequestBody ClientUser clientUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
