package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.system.api.domain.ClientUser;

/**
 * 账户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-26
 */
public interface ClientUserMapper 
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
     * 删除账户信息
     * 
     * @param userId 账户信息主键
     * @return 结果
     */
    public int deleteClientUserByUserId(Long userId);

    /**
     * 批量删除账户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientUserByUserIds(Long[] userIds);
}
