package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.client.domain.ClientUserWalletFlow;
import com.ruoyi.client.mapper.ClientUserWalletFlowMapper;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientUserWalletMapper;
import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.client.service.IClientUserWalletService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户资产Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Slf4j
@Service
public class ClientUserWalletServiceImpl implements IClientUserWalletService
{
    @Autowired
    private ClientUserWalletMapper clientUserWalletMapper;

    @Autowired
    private ClientUserWalletFlowMapper clientUserWalletFlowMapper;

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
        clientUserWallet.setStatus(ClientConstant.WALLET_STATUS_NORMAL);
        clientUserWallet.setCreateBy(userName);
        clientUserWallet.setUpdateBy(userName);
        clientUserWallet.setCreateTime(new Date());
        clientUserWallet.setUpdateTime(new Date());
        return clientUserWalletMapper.insertClientUserWallet(clientUserWallet) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void balanceChange(Long userId, String operator, Long bizOrderId, String currency, BigDecimal amount, String direct) {
        ClientUserWallet clientUserWallet = getUserWallet(userId, currency);
        log.info("获取到用户钱包信息:{}", clientUserWallet);
        if (ClientConstant.REDUCE.equals(direct) && clientUserWallet.getTotalAmount().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Balance not enough");
        }
        clientUserWalletMapper.updateWalletBalanceById(clientUserWallet.getId(), amount, direct, operator);
        ClientUserWalletFlow clientUserWalletFlow = new ClientUserWalletFlow();
        clientUserWalletFlow.setWalletId(clientUserWallet.getId());
        clientUserWalletFlow.setUserId(userId);
        clientUserWalletFlow.setBizOrderId(bizOrderId);
        clientUserWalletFlow.setType("FUND");
        clientUserWalletFlow.setDirect(direct);
        clientUserWalletFlow.setAmount(amount);
        clientUserWalletFlow.setCurrency(currency);
        clientUserWalletFlow.setCreateBy(operator);
        clientUserWalletFlow.setCreateTime(new Date());
        clientUserWalletFlow.setUpdateBy(operator);
        clientUserWalletFlow.setUpdateTime(new Date());
        clientUserWalletFlowMapper.insertClientUserWalletFlow(clientUserWalletFlow);
    }

    @Override
    public void balanceRollback(Long userId, String operator, String currency, Long bizOrderId) {
        ClientUserWallet clientUserWallet = getUserWallet(userId, currency);
        log.info("获取到用户钱包信息:{}", clientUserWallet);
        ClientUserWalletFlow selectPParams = new ClientUserWalletFlow();
        selectPParams.setBizOrderId(bizOrderId);
        selectPParams.setDirect(ClientConstant.REDUCE);
        List<ClientUserWalletFlow> clientUserWalletFlows = clientUserWalletFlowMapper.selectClientUserWalletFlowList(selectPParams);
        if (CollectionUtils.size(clientUserWalletFlows) != 1) {
            throw new RuntimeException("Order is exception!");
        }
        ClientUserWalletFlow existsWalletFlow = clientUserWalletFlows.get(0);
        clientUserWalletMapper.updateWalletBalanceById(clientUserWallet.getId(), existsWalletFlow.getAmount(), ClientConstant.INCREASE, operator);
        ClientUserWalletFlow clientUserWalletFlow = new ClientUserWalletFlow();
        BeanUtils.copyProperties(existsWalletFlow, clientUserWalletFlow);
        clientUserWalletFlow.setId(null);
        clientUserWalletFlow.setDirect(ClientConstant.INCREASE);
        clientUserWalletFlow.setUpdateBy(operator);
        clientUserWalletFlow.setUpdateTime(new Date());
        clientUserWalletFlowMapper.insertClientUserWalletFlow(clientUserWalletFlow);
    }

    @Override
    public ClientUserWallet getUserWallet(Long userId) {
        return getUserWallet(userId, "USD");
    }

    private ClientUserWallet getUserWallet(Long userId, String currency) {
        ClientUserWallet selectParams = new ClientUserWallet();
        selectParams.setUserId(userId);
        selectParams.setCurrency(currency);
        List<ClientUserWallet> clientUserWallets = clientUserWalletMapper.selectClientUserWalletList(selectParams);
        if (CollectionUtils.isEmpty(clientUserWallets)) {
            throw new RuntimeException("Wallet is not exists!");
        }
        ClientUserWallet clientUserWallet = clientUserWallets.get(0);
        if (!ClientConstant.WALLET_STATUS_NORMAL.equals(clientUserWallet.getStatus())) {
            throw new RuntimeException("Wallet is exception!");
        }
        return clientUserWallet;
    }
}
