package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.ruoyi.client.controller.req.WithdrawCalculateReq;
import com.ruoyi.client.controller.req.WithdrawOrderSubmitReq;
import com.ruoyi.client.controller.resp.WithdrawCalculateResp;
import com.ruoyi.client.domain.FundAccountInfo;
import com.ruoyi.client.domain.TradeFeeConfig;
import com.ruoyi.client.mapper.FundAccountInfoMapper;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.client.service.ITradeFeeConfigService;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.uuid.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.WithdrawOrderMapper;
import com.ruoyi.client.domain.WithdrawOrder;
import com.ruoyi.client.service.IWithdrawOrderService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private FundAccountInfoMapper fundAccountInfoMapper;

    @Autowired
    private ITradeFeeConfigService tradeFeeConfigService;

    @Autowired
    private IClientUserWalletService clientUserWalletService;

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
        withdrawOrder.setCreateTime(DateUtils.getNowDate());
        withdrawOrder.setCreateBy(SecurityContextHolder.getUserName());
        withdrawOrder.setUpdateTime(DateUtils.getNowDate());
        withdrawOrder.setUpdateBy(SecurityContextHolder.getUserName());
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
        WithdrawOrder existOrder = withdrawOrderMapper.selectWithdrawOrderById(withdrawOrder.getId());
        if (existOrder == null) {
            throw new RuntimeException("订单不存在");
        }
        if (existOrder.getStatus().equals(1L) || existOrder.getStatus().equals(2L)) {
            throw new RuntimeException(("订单为终态，不可修改"));
        }
        if (Long.valueOf(2L).equals(withdrawOrder.getStatus())) {
            clientUserWalletService.balanceRollback(existOrder.getUserId(), SecurityContextHolder.getUserName(), existOrder.getCurrency(), existOrder.getId());
        }
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

    @Override
    public WithdrawCalculateResp calculate(WithdrawCalculateReq withdrawCalculateReq) {
        log.info("计算费用请求参数:{}", withdrawCalculateReq);
        FundAccountInfo fundAccountInfo = fundAccountInfoMapper.selectFundAccountInfoById(withdrawCalculateReq.getFundAcctId());
        if (fundAccountInfo == null) {
            throw new RuntimeException("Account is not exists!");
        }
        WithdrawCalculateResp withdrawCalculateResp = new WithdrawCalculateResp();
        withdrawCalculateResp.setFeeAmount(calculateFee(SecurityContextHolder.getUserId(), fundAccountInfo.getAccountType(), withdrawCalculateReq.getAmount()));
        withdrawCalculateResp.setReceivedAmount(withdrawCalculateReq.getAmount().subtract(withdrawCalculateResp.getFeeAmount()));
        return withdrawCalculateResp;
    }

    /**
     * 提交提现订单
     * @param withdrawOrderSubmitReq
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitWithdrawOrder(WithdrawOrderSubmitReq withdrawOrderSubmitReq) {
        WithdrawOrder withdrawOrder = createWithdrawOrder(withdrawOrderSubmitReq);
        log.info("生成提现订单:{}", withdrawOrder);
        clientUserWalletService.balanceChange(
                withdrawOrder.getUserId(),
                withdrawOrder.getUserName(),
                withdrawOrder.getId(),
                withdrawOrder.getCurrency(),
                withdrawOrder.getAmount(),
                ClientConstant.REDUCE
        );
    }

    private WithdrawOrder createWithdrawOrder(WithdrawOrderSubmitReq withdrawOrderSubmitReq) {
        String orderId = UUID.fastUUID().toString().replaceAll("[-_]", "");
        WithdrawOrder withdrawOrder = new WithdrawOrder();
        withdrawOrder.setUserId(2L);
        withdrawOrder.setUserName(SecurityContextHolder.getUserName());
        withdrawOrder.setOrderId(orderId);
        withdrawOrder.setAmount(withdrawOrderSubmitReq.getAmount());
        withdrawOrder.setFeeAmount(BigDecimal.ZERO);
        FundAccountInfo fundAccountInfo = fundAccountInfoMapper.selectFundAccountInfoById(withdrawOrderSubmitReq.getFundAcctId());
        if (fundAccountInfo == null) {
            throw new RuntimeException("Account is not exists!");
        }
        withdrawOrder.setCurrency(withdrawOrderSubmitReq.getCurrency());
        withdrawOrder.setFundAcct(fundAccountInfo.getAccountNo());
        withdrawOrder.setFeeAmount(calculateFee(SecurityContextHolder.getUserId(), fundAccountInfo.getAccountType(), withdrawOrderSubmitReq.getAmount()));
        withdrawOrder.setReceivedAmount(withdrawOrder.getAmount().subtract(withdrawOrder.getFeeAmount()));
        withdrawOrder.setStatus(0L);
        withdrawOrder.setRemark(withdrawOrderSubmitReq.getRemark());
        withdrawOrderMapper.insertWithdrawOrder(withdrawOrder);
        return withdrawOrder;
    }

    private BigDecimal calculateFee(Long userId, String accountType, BigDecimal withdrawAmount) {
        TradeFeeConfig tradeFeeConfig = tradeFeeConfigService.selectTradeConfig(userId, ClientConstant.WITHDRAW, accountType);
        if (tradeFeeConfig == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (tradeFeeConfig.getFeeType().equals(ClientConstant.FIXED_FEE)) {
            feeAmount = new BigDecimal(tradeFeeConfig.getContent());
        } else if (tradeFeeConfig.getFeeType().equals(ClientConstant.RATE_FEE)) {
            feeAmount = new BigDecimal(tradeFeeConfig.getContent()).multiply(withdrawAmount).setScale(2, RoundingMode.HALF_UP);
        }
        if (withdrawAmount.compareTo(feeAmount) <= 0) {
            feeAmount = withdrawAmount;
        }
        return feeAmount;
    }
}
