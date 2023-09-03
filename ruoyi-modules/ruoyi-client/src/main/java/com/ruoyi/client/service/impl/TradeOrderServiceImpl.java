package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.TradeOrderMapper;
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.ITradeOrderService;

/**
 * 交易订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
@Service
public class TradeOrderServiceImpl implements ITradeOrderService 
{
    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    /**
     * 查询交易订单
     * 
     * @param id 交易订单主键
     * @return 交易订单
     */
    @Override
    public TradeOrder selectTradeOrderById(Long id)
    {
        return tradeOrderMapper.selectTradeOrderById(id);
    }

    /**
     * 查询交易订单列表
     * 
     * @param tradeOrder 交易订单
     * @return 交易订单
     */
    @Override
    public List<TradeOrder> selectTradeOrderList(TradeOrder tradeOrder)
    {
        return tradeOrderMapper.selectTradeOrderList(tradeOrder);
    }

    /**
     * 新增交易订单
     * 
     * @param tradeOrder 交易订单
     * @return 结果
     */
    @Override
    public int insertTradeOrder(TradeOrder tradeOrder)
    {
        return tradeOrderMapper.insertTradeOrder(tradeOrder);
    }

    /**
     * 修改交易订单
     * 
     * @param tradeOrder 交易订单
     * @return 结果
     */
    @Override
    public int updateTradeOrder(TradeOrder tradeOrder)
    {
        return tradeOrderMapper.updateTradeOrder(tradeOrder);
    }

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的交易订单主键
     * @return 结果
     */
    @Override
    public int deleteTradeOrderByIds(Long[] ids)
    {
        return tradeOrderMapper.deleteTradeOrderByIds(ids);
    }

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单主键
     * @return 结果
     */
    @Override
    public int deleteTradeOrderById(Long id)
    {
        return tradeOrderMapper.deleteTradeOrderById(id);
    }
}
