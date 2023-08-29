package com.ruoyi.client.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.RechargeOrderMapper;
import com.ruoyi.client.domain.RechargeOrder;
import com.ruoyi.client.service.IRechargeOrderService;

/**
 * 充值订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class RechargeOrderServiceImpl implements IRechargeOrderService 
{
    @Autowired
    private RechargeOrderMapper rechargeOrderMapper;

    /**
     * 查询充值订单
     * 
     * @param id 充值订单主键
     * @return 充值订单
     */
    @Override
    public RechargeOrder selectRechargeOrderById(Long id)
    {
        return rechargeOrderMapper.selectRechargeOrderById(id);
    }

    /**
     * 查询充值订单列表
     * 
     * @param rechargeOrder 充值订单
     * @return 充值订单
     */
    @Override
    public List<RechargeOrder> selectRechargeOrderList(RechargeOrder rechargeOrder)
    {
        return rechargeOrderMapper.selectRechargeOrderList(rechargeOrder);
    }

    /**
     * 新增充值订单
     * 
     * @param rechargeOrder 充值订单
     * @return 结果
     */
    @Override
    public int insertRechargeOrder(RechargeOrder rechargeOrder)
    {
        rechargeOrder.setCreateTime(DateUtils.getNowDate());
        return rechargeOrderMapper.insertRechargeOrder(rechargeOrder);
    }

    /**
     * 修改充值订单
     * 
     * @param rechargeOrder 充值订单
     * @return 结果
     */
    @Override
    public int updateRechargeOrder(RechargeOrder rechargeOrder)
    {
        rechargeOrder.setUpdateTime(DateUtils.getNowDate());
        return rechargeOrderMapper.updateRechargeOrder(rechargeOrder);
    }

    /**
     * 批量删除充值订单
     * 
     * @param ids 需要删除的充值订单主键
     * @return 结果
     */
    @Override
    public int deleteRechargeOrderByIds(Long[] ids)
    {
        return rechargeOrderMapper.deleteRechargeOrderByIds(ids);
    }

    /**
     * 删除充值订单信息
     * 
     * @param id 充值订单主键
     * @return 结果
     */
    @Override
    public int deleteRechargeOrderById(Long id)
    {
        return rechargeOrderMapper.deleteRechargeOrderById(id);
    }
}
