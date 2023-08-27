package com.ruoyi.auth.controller;

import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.service.ClientLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("/client")
public class ClientTokenController {

    @Resource
    private TokenService tokenService;

    @Resource
    private ClientLoginService clientLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {

        // 用户登录
        LoginUser userInfo = clientLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

}
