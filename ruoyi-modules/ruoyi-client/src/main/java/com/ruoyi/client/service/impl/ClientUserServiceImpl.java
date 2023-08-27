package com.ruoyi.client.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientUserMapper;
import com.ruoyi.system.api.domain.ClientUser;
import com.ruoyi.client.service.IClientUserService;

/**
 * 账户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-13
 */
@Service
public class ClientUserServiceImpl implements IClientUserService 
{
    @Autowired
    private ClientUserMapper clientUserMapper;

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
}
