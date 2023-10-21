package com.ruoyi.auth.service;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteClientUserService;
import com.ruoyi.system.api.domain.ClientUser;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientLoginService {

    private final Logger logger = LoggerFactory.getLogger(ClientLoginService.class);

    @Resource
    private RemoteClientUserService remoteClientUserService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new ServiceException("Username or password can not blank!");
        }
        R<LoginUser> userResult = remoteClientUserService.getUserInfo(username, SecurityConstants.INNER);
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            throw new ServiceException("User is not exists!");
        }

        if (R.FAIL == userResult.getCode())
        {
            throw new ServiceException(userResult.getMsg());
        }
        LoginUser userInfo = userResult.getData();
        logger.info("获取到登录用户信息:{}", userInfo);
        ClientUser user = userResult.getData().getClientUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            throw new ServiceException("Sorry, your account has delete!");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new ServiceException("Sorry, your account has hang up!");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            throw new ServiceException("Your username or password is not match~");
        }
        return userInfo;
    }

    public void register(String username, String password, String phonenumber, Long inviteCode) {
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new ServiceException("username/password is not blank");
        }
        logger.info("客户端注册账号：{},{}", username, password);
        ClientUser clientUser = new ClientUser();
        clientUser.setUserName(username);
        clientUser.setNickName(username);
        clientUser.setPhonenumber(phonenumber);
        clientUser.setInviteCode(inviteCode);
        clientUser.setPassword(SecurityUtils.encryptPassword(password));
        R<Boolean> registerResult = remoteClientUserService.registerUserInfo(clientUser, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode())
        {
            throw new ServiceException(registerResult.getMsg());
        }
    }

}
