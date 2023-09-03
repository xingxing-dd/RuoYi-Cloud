package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.WithdrawOrder;

/**
 * 提现订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
public interface WithdrawOrderMapper 
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
     * 删除提现订单
     * 
     * @param id 提现订单主键
     * @return 结果
     */
    public int deleteWithdrawOrderById(Long id);

    /**
     * 批量删除提现订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWithdrawOrderByIds(Long[] ids);
}
