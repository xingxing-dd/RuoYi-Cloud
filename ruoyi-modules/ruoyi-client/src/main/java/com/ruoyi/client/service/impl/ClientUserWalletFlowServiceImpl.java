package com.ruoyi.client.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientUserWalletFlowMapper;
import com.ruoyi.client.domain.ClientUserWalletFlow;
import com.ruoyi.client.service.IClientUserWalletFlowService;

/**
 * 用户钱包流水Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class ClientUserWalletFlowServiceImpl implements IClientUserWalletFlowService 
{
    @Autowired
    private ClientUserWalletFlowMapper clientUserWalletFlowMapper;

    /**
     * 查询用户钱包流水
     * 
     * @param id 用户钱包流水主键
     * @return 用户钱包流水
     */
    @Override
    public ClientUserWalletFlow selectClientUserWalletFlowById(Long id)
    {
        return clientUserWalletFlowMapper.selectClientUserWalletFlowById(id);
    }

    /**
     * 查询用户钱包流水列表
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 用户钱包流水
     */
    @Override
    public List<ClientUserWalletFlow> selectClientUserWalletFlowList(ClientUserWalletFlow clientUserWalletFlow)
    {
        return clientUserWalletFlowMapper.selectClientUserWalletFlowList(clientUserWalletFlow);
    }

    /**
     * 新增用户钱包流水
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 结果
     */
    @Override
    public int insertClientUserWalletFlow(ClientUserWalletFlow clientUserWalletFlow)
    {
        clientUserWalletFlow.setCreateTime(DateUtils.getNowDate());
        return clientUserWalletFlowMapper.insertClientUserWalletFlow(clientUserWalletFlow);
    }

    /**
     * 修改用户钱包流水
     * 
     * @param clientUserWalletFlow 用户钱包流水
     * @return 结果
     */
    @Override
    public int updateClientUserWalletFlow(ClientUserWalletFlow clientUserWalletFlow)
    {
        clientUserWalletFlow.setUpdateTime(DateUtils.getNowDate());
        return clientUserWalletFlowMapper.updateClientUserWalletFlow(clientUserWalletFlow);
    }

    /**
     * 批量删除用户钱包流水
     * 
     * @param ids 需要删除的用户钱包流水主键
     * @return 结果
     */
    @Override
    public int deleteClientUserWalletFlowByIds(Long[] ids)
    {
        return clientUserWalletFlowMapper.deleteClientUserWalletFlowByIds(ids);
    }

    /**
     * 删除用户钱包流水信息
     * 
     * @param id 用户钱包流水主键
     * @return 结果
     */
    @Override
    public int deleteClientUserWalletFlowById(Long id)
    {
        return clientUserWalletFlowMapper.deleteClientUserWalletFlowById(id);
    }
}
