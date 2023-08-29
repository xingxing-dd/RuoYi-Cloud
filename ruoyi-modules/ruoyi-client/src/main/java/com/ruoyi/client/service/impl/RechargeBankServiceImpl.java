package com.ruoyi.client.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.RechargeBankMapper;
import com.ruoyi.client.domain.RechargeBank;
import com.ruoyi.client.service.IRechargeBankService;

/**
 * 充值银行信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class RechargeBankServiceImpl implements IRechargeBankService 
{
    @Autowired
    private RechargeBankMapper rechargeBankMapper;

    /**
     * 查询充值银行信息
     * 
     * @param id 充值银行信息主键
     * @return 充值银行信息
     */
    @Override
    public RechargeBank selectRechargeBankById(Long id)
    {
        return rechargeBankMapper.selectRechargeBankById(id);
    }

    /**
     * 查询充值银行信息列表
     * 
     * @param rechargeBank 充值银行信息
     * @return 充值银行信息
     */
    @Override
    public List<RechargeBank> selectRechargeBankList(RechargeBank rechargeBank)
    {
        return rechargeBankMapper.selectRechargeBankList(rechargeBank);
    }

    /**
     * 新增充值银行信息
     * 
     * @param rechargeBank 充值银行信息
     * @return 结果
     */
    @Override
    public int insertRechargeBank(RechargeBank rechargeBank)
    {
        rechargeBank.setCreateTime(DateUtils.getNowDate());
        return rechargeBankMapper.insertRechargeBank(rechargeBank);
    }

    /**
     * 修改充值银行信息
     * 
     * @param rechargeBank 充值银行信息
     * @return 结果
     */
    @Override
    public int updateRechargeBank(RechargeBank rechargeBank)
    {
        rechargeBank.setUpdateTime(DateUtils.getNowDate());
        return rechargeBankMapper.updateRechargeBank(rechargeBank);
    }

    /**
     * 批量删除充值银行信息
     * 
     * @param ids 需要删除的充值银行信息主键
     * @return 结果
     */
    @Override
    public int deleteRechargeBankByIds(Long[] ids)
    {
        return rechargeBankMapper.deleteRechargeBankByIds(ids);
    }

    /**
     * 删除充值银行信息信息
     * 
     * @param id 充值银行信息主键
     * @return 结果
     */
    @Override
    public int deleteRechargeBankById(Long id)
    {
        return rechargeBankMapper.deleteRechargeBankById(id);
    }
}
