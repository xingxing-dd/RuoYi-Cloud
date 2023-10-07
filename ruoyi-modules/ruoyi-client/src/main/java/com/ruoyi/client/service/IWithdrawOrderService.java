package com.ruoyi.client.service;

import java.util.List;

import com.ruoyi.client.controller.req.WithdrawCalculateReq;
import com.ruoyi.client.controller.req.WithdrawOrderSubmitReq;
import com.ruoyi.client.controller.resp.WithdrawCalculateResp;
import com.ruoyi.client.domain.WithdrawOrder;

/**
 * 提现订单Service接口
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
public interface IWithdrawOrderService 
{
    /**
     * 查询提现订单
     * 
     * @param id 提现订单主键
     * @return 提现订单
     */
    public WithdrawOrder selectWithdrawOrderById(Long id);

    /**
     * 查询提现订单列表
     * 
     * @param withdrawOrder 提现订单
     * @return 提现订单集合
     */
    public List<WithdrawOrder> selectWithdrawOrderList(WithdrawOrder withdrawOrder);

    /**
     * 新增提现订单
     * 
     * @param withdrawOrder 提现订单
     * @return 结果
     */
    public int insertWithdrawOrder(WithdrawOrder withdrawOrder);

    /**
     * 修改提现订单
     * 
     * @param withdrawOrder 提现订单
     * @return 结果
     */
    public int updateWithdrawOrder(WithdrawOrder withdrawOrder);

    /**
     * 批量删除提现订单
     * 
     * @param ids 需要删除的提现订单主键集合
     * @return 结果
     */
    public int deleteWithdrawOrderByIds(Long[] ids);

    /**
     * 删除提现订单信息
     * 
     * @param id 提现订单主键
     * @return 结果
     */
    public int deleteWithdrawOrderById(Long id);

    /**
     * 计算提现费用
     * @param withdrawCalculateReq
     */
    public WithdrawCalculateResp calculate(WithdrawCalculateReq withdrawCalculateReq);

    /**
     * 提交提现订单
     * @param withdrawOrderSubmitReq
     */
    public void submitWithdrawOrder(WithdrawOrderSubmitReq withdrawOrderSubmitReq);
}
