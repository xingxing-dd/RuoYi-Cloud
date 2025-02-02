package com.ruoyi.client.service.impl;

import com.ruoyi.client.controller.resp.InviteRegisterResp;
import com.ruoyi.client.domain.ClientUser;
import com.ruoyi.client.mapper.ClientUserMapper;
import com.ruoyi.client.service.IClientUserService;
import com.ruoyi.client.utils.QrCodeUtils;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.exception.base.BaseException;
import com.ruoyi.common.core.utils.ExceptionUtil;
import com.ruoyi.common.redis.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 账户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-13
 */
@Service
public class ClientUserServiceImpl implements IClientUserService 
{
    @Resource
    private ClientUserMapper clientUserMapper;

    @Resource
    private RedisService redisService;

    @Value("${website.register.url:http://xingxingdd.info/register?inviteCode=%s}")
    private String registerUrl;

    /**
     * 查询账户信息
     * 
     * @param userId 账户信息主键
     * @return 账户信息
     */
    @Override
    public ClientUser selectClientUserByUserId(Long userId)
    {
        return clientUserMapper.selectClientUserByUserId(userId);
    }

    /**
     * 查询账户信息列表
     * 
     * @param clientUser 账户信息
     * @return 账户信息
     */
    @Override
    public List<ClientUser> selectClientUserList(ClientUser clientUser)
    {
        return clientUserMapper.selectClientUserList(clientUser);
    }

    /**
     * 新增账户信息
     * 
     * @param clientUser 账户信息
     * @return 结果
     */
    @Override
    public int insertClientUser(ClientUser clientUser)
    {
        return clientUserMapper.insertClientUser(clientUser);
    }

    /**
     * 修改账户信息
     * 
     * @param clientUser 账户信息
     * @return 结果
     */
    @Override
    public int updateClientUser(ClientUser clientUser)
    {
        return clientUserMapper.updateClientUser(clientUser);
    }

    /**
     * 批量删除账户信息
     * 
     * @param userIds 需要删除的账户信息主键
     * @return 结果
     */
    @Override
    public int deleteClientUserByUserIds(Long[] userIds)
    {
        return clientUserMapper.deleteClientUserByUserIds(userIds);
    }

    /**
     * 删除账户信息信息
     * 
     * @param userId 账户信息主键
     * @return 结果
     */
    @Override
    public int deleteClientUserByUserId(Long userId)
    {
        return clientUserMapper.deleteClientUserByUserId(userId);
    }

    @Override
    public ClientUser selectUserByUserName(String username) {
        ClientUser clientUser = new ClientUser();
        clientUser.setUserName(username);
        List<ClientUser> clientUsers = clientUserMapper.selectClientUserList(clientUser);
        if (CollectionUtils.isEmpty(clientUsers)) {
            return null;
        }
        return clientUsers.get(0);
    }

    @Override
    public boolean register(ClientUser clientUser) {
        Integer userSequence = redisService.getCacheObject("ruoyi-client:user:sequence");
        if (userSequence == null) {
            userSequence = 1;
        }
        redisService.setCacheObject("ruoyi-client:user:sequence", userSequence + 1);
        String userId = String.format("1%06d", userSequence);
        clientUser.setUserId(Long.valueOf(userId));
        clientUser.setEmail(clientUser.getUserName());
        clientUser.setStatus(0L);
        clientUser.setDelFlag(0L);
        clientUser.setCreateBy(clientUser.getUserName());
        clientUser.setUpdateBy(clientUser.getUserName());
        clientUser.setCreateTime(new Date());
        clientUser.setUpdateTime(new Date());
        return clientUserMapper.insertClientUser(clientUser) == 1;
    }

    @Override
    public InviteRegisterResp generateInviteUrl() {
        String url = String.format(registerUrl, SecurityContextHolder.getUserId());
        InviteRegisterResp inviteRegisterResp = new InviteRegisterResp();
        inviteRegisterResp.setInviteUrl(url);
        inviteRegisterResp.setInviteQrcode(QrCodeUtils.createCode(url));
        return inviteRegisterResp;
    }
}
