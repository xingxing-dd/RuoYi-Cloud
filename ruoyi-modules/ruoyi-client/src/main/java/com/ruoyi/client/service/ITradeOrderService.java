package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.TradeOrder;

/**
 * 交易订单Service接口
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
public interface ITradeOrderService 
{
    /**
     * 查询交易订单
     * 
     * @param id 交易订单主键
     * @return 交易订单
     */
    public TradeOrder selectTradeOrderById(Long id);

    /**
     * 查询交易订单列表
     * 
     * @param tradeOrder 交易订单
     * @return 交易订单集合
     */
    public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder);

    /**
     * 新增交易订单
     * 
     * @param tradeOrder 交易订单
     * @return 结果
     */
    public int insertTradeOrder(TradeOrder tradeOrder);

    /**
     * 修改交易订单
     * 
     * @param tradeOrder 交易订单
     * @return 结果
     */
    public int updateTradeOrder(TradeOrder tradeOrder);

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的交易订单主键集合
     * @return 结果
     */
    public int deleteTradeOrderByIds(Long[] ids);

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单主键
     * @return 结果
     */
    public int deleteTradeOrderById(Long id);

    /**
     * 交易订单
     * @param tradeOrder
     */
    public boolean submit(TradeOrder tradeOrder);

    /**
     * 平仓
     * @param tradeOrder
     * @return
     */
    public boolean sellOut(TradeOrder tradeOrder);
}
