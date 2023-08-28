package com.ruoyi.auth.service;

import com.ruoyi.system.api.RemoteClientUserService;
import com.ruoyi.system.api.domain.ClientUser;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientLoginService {

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
            throw new ServiceException("用户/密码必须填写");
        }
        R<LoginUser> userResult = remoteClientUserService.getUserInfo(username, SecurityConstants.INNER);
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (R.FAIL == userResult.getCode())
        {
            throw new ServiceException(userResult.getMsg());
        }
        LoginUser userInfo = userResult.getData();
        ClientUser user = userResult.getData().getClientUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        return userInfo;
    }

    public void register(String username, String password) {

    }

}
