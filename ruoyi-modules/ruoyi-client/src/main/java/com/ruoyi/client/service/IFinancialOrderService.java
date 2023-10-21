package com.ruoyi.client.service;

import java.util.List;

import com.ruoyi.client.controller.resp.FinancialOrderResp;
import com.ruoyi.client.domain.FinancialOrder;

/**
 * 余额宝订单Service接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface IFinancialOrderService 
{
    /**
     * 查询余额宝订单
     * 
     * @param id 余额宝订单主键
     * @return 余额宝订单
     */
    public FinancialOrder selectFinancialOrderById(Long id);

    /**
     * 查询余额宝订单列表
     * 
     * @param financialOrder 余额宝订单
     * @return 余额宝订单集合
     */
    public List<FinancialOrder> selectFinancialOrderList(FinancialOrder financialOrder);

    /**
     * 新增余额宝订单
     * 
     * @param financialOrder 余额宝订单
     * @return 结果
     */
    public int insertFinancialOrder(FinancialOrder financialOrder);

    /**
     * 修改余额宝订单
     * 
     * @param financialOrder 余额宝订单
     * @return 结果
     */
    public int updateFinancialOrder(FinancialOrder financialOrder);

    /**
     * 批量删除余额宝订单
     * 
     * @param ids 需要删除的余额宝订单主键集合
     * @return 结果
     */
    public int deleteFinancialOrderByIds(Long[] ids);

    /**
     * 删除余额宝订单信息
     * 
     * @param id 余额宝订单主键
     * @return 结果
     */
    public int deleteFinancialOrderById(Long id);

    /**
     * 查询当前用户的
     * @return
     */
    public FinancialOrderResp queryFinancialOrder();
}
