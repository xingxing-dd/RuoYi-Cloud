package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.common.core.utils.uuid.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.WithdrawOrderMapper;
import com.ruoyi.client.domain.WithdrawOrder;
import com.ruoyi.client.service.IWithdrawOrderService;

/**
 * 提现订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
@Slf4j
@Service
public class WithdrawOrderServiceImpl implements IWithdrawOrderService 
{
    @Autowired
    private WithdrawOrderMapper withdrawOrderMapper;

    /**
     * 查询提现订单
     * 
     * @param id 提现订单主键
     * @return 提现订单
     */
    @Override
    public WithdrawOrder selectWithdrawOrderById(Long id)
    {
        return withdrawOrderMapper.selectWithdrawOrderById(id);
    }

    /**
     * 查询提现订单列表
     * 
     * @param withdrawOrder 提现订单
     * @return 提现订单
     */
    @Override
    public List<WithdrawOrder> selectWithdrawOrderList(WithdrawOrder withdrawOrder)
    {
        return withdrawOrderMapper.selectWithdrawOrderList(withdrawOrder);
    }

    /**
     * 新增提现订单
     * 
     * @param withdrawOrder 提现订单
     * @return 结果
     */
    @Override
    public int insertWithdrawOrder(WithdrawOrder withdrawOrder)
    {
        return withdrawOrderMapper.insertWithdrawOrder(withdrawOrder);
    }

    /**
     * 修改提现订单
     * 
     * @param withdrawOrder 提现订单
     * @return 结果
     */
    @Override
    public int updateWithdrawOrder(WithdrawOrder withdrawOrder)
    {
        return withdrawOrderMapper.updateWithdrawOrder(withdrawOrder);
    }

    /**
     * 批量删除提现订单
     * 
     * @param ids 需要删除的提现订单主键
     * @return 结果
     */
    @Override
    public int deleteWithdrawOrderByIds(Long[] ids)
    {
        return withdrawOrderMapper.deleteWithdrawOrderByIds(ids);
    }

    /**
     * 删除提现订单信息
     * 
     * @param id 提现订单主键
     * @return 结果
     */
    @Override
    public int deleteWithdrawOrderById(Long id)
    {
        return withdrawOrderMapper.deleteWithdrawOrderById(id);
    }

    /**
     * 提交提现订单
     * @param withdrawOrder
     */
    @Override
    public void submitWithdrawOrder(WithdrawOrder withdrawOrder) {
        String orderId = UUID.fastUUID().toString();
        log.info("生成订单号:{}", orderId);
        withdrawOrder.setOrderId(orderId);
        withdrawOrder.setStatus(0L);
        withdrawOrder.setFeeAmount(BigDecimal.ZERO);
        withdrawOrderMapper.insertWithdrawOrder(withdrawOrder);
    }
}
