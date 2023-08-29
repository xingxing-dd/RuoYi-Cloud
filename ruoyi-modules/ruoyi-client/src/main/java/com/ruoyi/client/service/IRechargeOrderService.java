package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.RechargeOrder;

/**
 * 充值订单Service接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface IRechargeOrderService 
{
    /**
     * 查询充值订单
     * 
     * @param id 充值订单主键
     * @return 充值订单
     */
    public RechargeOrder selectRechargeOrderById(Long id);

    /**
     * 查询充值订单列表
     * 
     * @param rechargeOrder 充值订单
     * @return 充值订单集合
     */
    public List<RechargeOrder> selectRechargeOrderList(RechargeOrder rechargeOrder);

    /**
     * 新增充值订单
     * 
     * @param rechargeOrder 充值订单
     * @return 结果
     */
    public int insertRechargeOrder(RechargeOrder rechargeOrder);

    /**
     * 修改充值订单
     * 
     * @param rechargeOrder 充值订单
     * @return 结果
     */
    public int updateRechargeOrder(RechargeOrder rechargeOrder);

    /**
     * 批量删除充值订单
     * 
     * @param ids 需要删除的充值订单主键集合
     * @return 结果
     */
    public int deleteRechargeOrderByIds(Long[] ids);

    /**
     * 删除充值订单信息
     * 
     * @param id 充值订单主键
     * @return 结果
     */
    public int deleteRechargeOrderById(Long id);
}
