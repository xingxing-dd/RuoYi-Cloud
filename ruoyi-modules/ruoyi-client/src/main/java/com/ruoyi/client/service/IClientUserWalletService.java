package com.ruoyi.client.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.client.domain.ClientUserWallet;

/**
 * 用户资产Service接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface IClientUserWalletService 
{
    /**
     * 查询用户资产
     * 
     * @param id 用户资产主键
     * @return 用户资产
     */
    public ClientUserWallet selectClientUserWalletById(Long id);

    public ClientUserWallet selectClientUserWalletByUserId(Long id);

    /**
     * 查询用户资产列表
     * 
     * @param clientUserWallet 用户资产
     * @return 用户资产集合
     */
    public List<ClientUserWallet> selectClientUserWalletList(ClientUserWallet clientUserWallet);

    /**
     * 新增用户资产
     * 
     * @param clientUserWallet 用户资产
     * @return 结果
     */
    public int insertClientUserWallet(ClientUserWallet clientUserWallet);

    /**
     * 修改用户资产
     * 
     * @param clientUserWallet 用户资产
     * @return 结果
     */
    public int updateClientUserWallet(ClientUserWallet clientUserWallet);

    /**
     * 批量删除用户资产
     * 
     * @param ids 需要删除的用户资产主键集合
     * @return 结果
     */
    public int deleteClientUserWalletByIds(Long[] ids);

    /**
     * 删除用户资产信息
     * 
     * @param id 用户资产主键
     * @return 结果
     */
    public int deleteClientUserWalletById(Long id);

    /**
     * 创建用户钱包账户
     * @param userId
     * @param userName
     * @return
     */
    public boolean createUserWallet(long userId, String userName);

    /**
     * 余额变化
     * @param userId
     * @param currency
     * @param amount
     * @param direct
     * @return
     */
    public void balanceChange(Long userId, String operator, Long bizOrderId, String currency, BigDecimal amount, String direct);

    /**
     * 余额回滚
     * @param userId
     * @param operator
     * @param bizOrderId
     */
    public void balanceRollback(Long userId, String operator, String currency, Long bizOrderId);
}
