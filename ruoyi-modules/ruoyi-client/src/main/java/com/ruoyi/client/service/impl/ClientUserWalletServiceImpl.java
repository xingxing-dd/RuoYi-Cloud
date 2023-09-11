package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientUserWalletMapper;
import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.client.service.IClientUserWalletService;

/**
 * 用户资产Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class ClientUserWalletServiceImpl implements IClientUserWalletService
{
    @Autowired
    private ClientUserWalletMapper clientUserWalletMapper;

    /**
     * 查询用户资产
     * 
     * @param id 用户资产主键
     * @return 用户资产
     */
    @Override
    public ClientUserWallet selectClientUserWalletById(Long id)
    {
        return clientUserWalletMapper.selectClientUserWalletById(id);
    }

    @Override
    public ClientUserWallet selectClientUserWalletByUserId(Long userId) {
        ClientUserWallet params = new ClientUserWallet();
        params.setUserId(userId);
        List<ClientUserWallet> clientUserWallets = clientUserWalletMapper.selectClientUserWalletList(params);
        if (CollectionUtils.isEmpty(clientUserWallets)) {
            return null;
        }
        return clientUserWallets.get(0);
    }

    /**
     * 查询用户资产列表
     * 
     * @param clientUserWallet 用户资产
     * @return 用户资产
     */
    @Override
    public List<ClientUserWallet> selectClientUserWalletList(ClientUserWallet clientUserWallet)
    {
        return clientUserWalletMapper.selectClientUserWalletList(clientUserWallet);
    }

    /**
     * 新增用户资产
     * 
     * @param clientUserWallet 用户资产
     * @return 结果
     */
    @Override
    public int insertClientUserWallet(ClientUserWallet clientUserWallet)
    {
        clientUserWallet.setCreateTime(DateUtils.getNowDate());
        return clientUserWalletMapper.insertClientUserWallet(clientUserWallet);
    }

    /**
     * 修改用户资产
     * 
     * @param clientUserWallet 用户资产
     * @return 结果
     */
    @Override
    public int updateClientUserWallet(ClientUserWallet clientUserWallet)
    {
        clientUserWallet.setUpdateTime(DateUtils.getNowDate());
        return clientUserWalletMapper.updateClientUserWallet(clientUserWallet);
    }

    /**
     * 批量删除用户资产
     * 
     * @param ids 需要删除的用户资产主键
     * @return 结果
     */
    @Override
    public int deleteClientUserWalletByIds(Long[] ids)
    {
        return clientUserWalletMapper.deleteClientUserWalletByIds(ids);
    }

    /**
     * 删除用户资产信息
     * 
     * @param id 用户资产主键
     * @return 结果
     */
    @Override
    public int deleteClientUserWalletById(Long id)
    {
        return clientUserWalletMapper.deleteClientUserWalletById(id);
    }

    @Override
    public boolean createUserWallet(long userId, String userName) {
        ClientUserWallet clientUserWallet = new ClientUserWallet();
        clientUserWallet.setUserId(userId);
        clientUserWallet.setCurrency("USD");
        clientUserWallet.setTotalAmount(BigDecimal.ZERO);
        clientUserWallet.setStatus("normal");
        clientUserWallet.setCreateBy(userName);
        clientUserWallet.setUpdateBy(userName);
        clientUserWallet.setCreateTime(new Date());
        clientUserWallet.setUpdateTime(new Date());
        return clientUserWalletMapper.insertClientUserWallet(clientUserWallet) == 1;
    }
}
