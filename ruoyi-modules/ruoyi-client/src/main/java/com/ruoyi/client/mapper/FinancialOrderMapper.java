package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.FinancialOrder;

/**
 * 余额宝订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface FinancialOrderMapper 
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
     * 删除余额宝订单
     * 
     * @param id 余额宝订单主键
     * @return 结果
     */
    public int deleteFinancialOrderById(Long id);

    /**
     * 批量删除余额宝订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialOrderByIds(Long[] ids);
}
