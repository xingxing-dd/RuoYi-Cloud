package com.ruoyi.client;

import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.common.core.constant.ServiceNameConstants;
import com.ruoyi.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "remoteClientWalletService", value = ServiceNameConstants.CLIENT_SERVICE)
public interface RemoteClientWalletService {

    @PostMapping("/wallet/detail")
    public R<ClientUserWallet> getUserWallet(@RequestBody ClientUserWallet clientUserWallet);

}
