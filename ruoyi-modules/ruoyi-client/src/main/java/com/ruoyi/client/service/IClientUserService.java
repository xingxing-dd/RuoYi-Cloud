package com.ruoyi.client.service;

import com.ruoyi.client.controller.resp.InviteRegisterResp;
import com.ruoyi.client.domain.ClientUser;

import java.util.List;

/**
 * 账户信息Service接口
 * 
 * @author ruoyi
 * @date 2023-08-13
 */
public interface IClientUserService 
{
    /**
     * 查询账户信息
     * 
     * @param userId 账户信息主键
     * @return 账户信息
     */
    public ClientUser selectClientUserByUserId(Long userId);

    /**
     * 查询账户信息列表
     * 
     * @param clientUser 账户信息
     * @return 账户信息集合
     */
    public List<ClientUser> selectClientUserList(ClientUser clientUser);

    /**
     * 新增账户信息
     * 
     * @param clientUser 账户信息
     * @return 结果
     */
    public int insertClientUser(ClientUser clientUser);

    /**
     * 修改账户信息
     * 
     * @param clientUser 账户信息
     * @return 结果
     */
    public int updateClientUser(ClientUser clientUser);

    /**
     * 批量删除账户信息
     * 
     * @param userIds 需要删除的账户信息主键集合
     * @return 结果
     */
    public int deleteClientUserByUserIds(Long[] userIds);

    /**
     * 删除账户信息信息
     * 
     * @param userId 账户信息主键
     * @return 结果
     */
    public int deleteClientUserByUserId(Long userId);

    public ClientUser selectUserByUserName(String username);

    /**
     * 账户注册
     * @param clientUser
     * @return
     */
    public boolean register(ClientUser clientUser);

    /**
     * 生成邀请数据
     * @return
     */
    public InviteRegisterResp generateInviteUrl();
}
