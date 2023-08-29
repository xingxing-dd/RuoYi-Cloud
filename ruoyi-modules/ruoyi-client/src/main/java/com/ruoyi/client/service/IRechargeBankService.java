package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.RechargeBank;

/**
 * 充值银行信息Service接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface IRechargeBankService 
{
    /**
     * 查询充值银行信息
     * 
     * @param id 充值银行信息主键
     * @return 充值银行信息
     */
    public RechargeBank selectRechargeBankById(Long id);

    /**
     * 查询充值银行信息列表
     * 
     * @param rechargeBank 充值银行信息
     * @return 充值银行信息集合
     */
    public List<RechargeBank> selectRechargeBankList(RechargeBank rechargeBank);

    /**
     * 新增充值银行信息
     * 
     * @param rechargeBank 充值银行信息
     * @return 结果
     */
    public int insertRechargeBank(RechargeBank rechargeBank);

    /**
     * 修改充值银行信息
     * 
     * @param rechargeBank 充值银行信息
     * @return 结果
     */
    public int updateRechargeBank(RechargeBank rechargeBank);

    /**
     * 批量删除充值银行信息
     * 
     * @param ids 需要删除的充值银行信息主键集合
     * @return 结果
     */
    public int deleteRechargeBankByIds(Long[] ids);

    /**
     * 删除充值银行信息信息
     * 
     * @param id 充值银行信息主键
     * @return 结果
     */
    public int deleteRechargeBankById(Long id);
}
