package com.ruoyi.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.ruoyi.auth.service.ClientLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.auth.form.LoginBody;
import com.ruoyi.auth.form.RegisterBody;
import com.ruoyi.auth.service.SysLoginService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author ruoyi
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private ClientLoginService clientLoginService;

    private final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form, @RequestHeader(value = "from", required = false) String from)
    {
        // 用户登录
        LoginUser userInfo;
        if (StringUtils.equals(from, "client")) {
            userInfo = clientLoginService.login(form.getUsername(), form.getPassword());
        } else {
            userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        }
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody, @RequestHeader(value = "from", required = false) String from)
    {
        // 用户注册
        if (StringUtils.equals(from, "client")) {
            clientLoginService.register(registerBody.getUsername(), registerBody.getPassword(), registerBody.getPhonenumber(), registerBody.getInviteCode());
        } else {
            sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        }
        return R.ok();
    }
}
