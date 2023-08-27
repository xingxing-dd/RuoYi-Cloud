package com.ruoyi.auth.service;

import com.ruoyi.system.api.RemoteClientService;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientLoginService {

    @Resource
    private RemoteClientService remoteClientService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public LoginUser login(String username, String password) {

        return null;
    }

}
