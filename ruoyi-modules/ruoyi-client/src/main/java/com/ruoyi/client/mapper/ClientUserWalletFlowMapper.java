package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.ClientUserWalletFlow;

/**
 * 用户钱包流水Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface ClientUserWalletFlowMapper 
{
    /**
     * 查询用户钱包流水
     * 
     * @param id 用户钱包流水主键
     * @return 用户钱包流水
     */
    public ClientUserWalletFlow selectClientUserWalletFlowById(Long id);

    /**
     * 查询用户钱包流水列表
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 用户钱包流水集合
     */
    public List<ClientUserWalletFlow> selectClientUserWalletFlowList(ClientUserWalletFlow clientUserWalletFlow);

    /**
     * 新增用户钱包流水
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 结果
     */
    public int insertClientUserWalletFlow(ClientUserWalletFlow clientUserWalletFlow);

    /**
     * 修改用户钱包流水
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 结果
     */
    public int updateClientUserWalletFlow(ClientUserWalletFlow clientUserWalletFlow);

    /**
     * 删除用户钱包流水
     * 
     * @param id 用户钱包流水主键
     * @return 结果
     */
    public int deleteClientUserWalletFlowById(Long id);

    /**
     * 批量删除用户钱包流水
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientUserWalletFlowByIds(Long[] ids);
}
