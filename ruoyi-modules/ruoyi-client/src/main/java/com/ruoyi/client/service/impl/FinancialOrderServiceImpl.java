package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.FinancialOrderMapper;
import com.ruoyi.client.domain.FinancialOrder;
import com.ruoyi.client.service.IFinancialOrderService;

/**
 * 余额宝订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class FinancialOrderServiceImpl implements IFinancialOrderService 
{
    @Autowired
    private FinancialOrderMapper financialOrderMapper;

    /**
     * 查询余额宝订单
     * 
     * @param id 余额宝订单主键
     * @return 余额宝订单
     */
    @Override
    public FinancialOrder selectFinancialOrderById(Long id)
    {
        return financialOrderMapper.selectFinancialOrderById(id);
    }

    /**
     * 查询余额宝订单列表
     * 
     * @param financialOrder 余额宝订单
     * @return 余额宝订单
     */
    @Override
    public List<FinancialOrder> selectFinancialOrderList(FinancialOrder financialOrder)
    {
        return financialOrderMapper.selectFinancialOrderList(financialOrder);
    }

    /**
     * 新增余额宝订单
     * 
     * @param financialOrder 余额宝订单
     * @return 结果
     */
    @Override
    public int insertFinancialOrder(FinancialOrder financialOrder)
    {
        return financialOrderMapper.insertFinancialOrder(financialOrder);
    }

    /**
     * 修改余额宝订单
     * 
     * @param financialOrder 余额宝订单
     * @return 结果
     */
    @Override
    public int updateFinancialOrder(FinancialOrder financialOrder)
    {
        return financialOrderMapper.updateFinancialOrder(financialOrder);
    }

    /**
     * 批量删除余额宝订单
     * 
     * @param ids 需要删除的余额宝订单主键
     * @return 结果
     */
    @Override
    public int deleteFinancialOrderByIds(Long[] ids)
    {
        return financialOrderMapper.deleteFinancialOrderByIds(ids);
    }

    /**
     * 删除余额宝订单信息
     * 
     * @param id 余额宝订单主键
     * @return 结果
     */
    @Override
    public int deleteFinancialOrderById(Long id)
    {
        return financialOrderMapper.deleteFinancialOrderById(id);
    }
}
