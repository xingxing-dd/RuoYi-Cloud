package com.ruoyi.client.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.uuid.UUID;
import com.ruoyi.market.api.RemoteProductInfoService;
import com.ruoyi.market.api.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.api.resp.ExchangeOrderCalculateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.EntrustOrderMapper;
import com.ruoyi.client.domain.EntrustOrder;
import com.ruoyi.client.service.IEntrustOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 委托订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
@Slf4j
@Service
public class EntrustOrderServiceImpl implements IEntrustOrderService 
{
    @Autowired
    private EntrustOrderMapper entrustOrderMapper;

    @Autowired
    private RemoteProductInfoService remoteProductInfoService;

    @Autowired
    private IClientUserWalletService clientUserWalletService;

    /**
     * 查询委托订单
     * 
     * @param id 委托订单主键
     * @return 委托订单
     */
    @Override
    public EntrustOrder selectEntrustOrderById(Long id)
    {
        return entrustOrderMapper.selectEntrustOrderById(id);
    }

    /**
     * 查询委托订单列表
     * 
     * @param entrustOrder 委托订单
     * @return 委托订单
     */
    @Override
    public List<EntrustOrder> selectEntrustOrderList(EntrustOrder entrustOrder)
    {
        return entrustOrderMapper.selectEntrustOrderList(entrustOrder);
    }

    /**
     * 新增委托订单
     * 
     * @param entrustOrder 委托订单
     * @return 结果
     */
    @Override
    public int insertEntrustOrder(EntrustOrder entrustOrder)
    {
        entrustOrder.setCreateTime(DateUtils.getNowDate());
        return entrustOrderMapper.insertEntrustOrder(entrustOrder);
    }

    /**
     * 修改委托订单
     * 
     * @param entrustOrder 委托订单
     * @return 结果
     */
    @Override
    public int updateEntrustOrder(EntrustOrder entrustOrder)
    {
        entrustOrder.setUpdateTime(DateUtils.getNowDate());
        return entrustOrderMapper.updateEntrustOrder(entrustOrder);
    }

    /**
     * 批量删除委托订单
     * 
     * @param ids 需要删除的委托订单主键
     * @return 结果
     */
    @Override
    public int deleteEntrustOrderByIds(Long[] ids)
    {
        return entrustOrderMapper.deleteEntrustOrderByIds(ids);
    }

    /**
     * 删除委托订单信息
     * 
     * @param id 委托订单主键
     * @return 结果
     */
    @Override
    public int deleteEntrustOrderById(Long id)
    {
        return entrustOrderMapper.deleteEntrustOrderById(id);
    }

    @Override
    public boolean submit(EntrustOrder entrustOrder) {
        String orderId = UUID.fastUUID().toString().replaceAll("[-_]", "");
        log.info("生成委托订单数据：{}", orderId);
        entrustOrder.setOrderId(orderId);
        entrustOrder.setUserId(SecurityContextHolder.getUserId());
        entrustOrder.setUserName(SecurityContextHolder.getUserName());
        entrustOrder.setStatus(ClientConstant.ENTRUST_PENDING);
        entrustOrder.setCreateTime(new Date());
        entrustOrder.setCreateBy(SecurityContextHolder.getUserName());
        entrustOrder.setUpdateTime(new Date());
        entrustOrder.setUpdateBy(SecurityContextHolder.getUserName());

        ExchangeOrderCalculateReq calculateReq = new ExchangeOrderCalculateReq();
        calculateReq.setProductCode(entrustOrder.getProductCode());
        calculateReq.setDirect(entrustOrder.getTradeDirect());
        calculateReq.setMultiplier(entrustOrder.getMultiplier());
        calculateReq.setExchangePrice(entrustOrder.getTradePrice());
        calculateReq.setSheetNum(entrustOrder.getSheetNum());
        R<ExchangeOrderCalculateVo> response = remoteProductInfoService.calculate(calculateReq);
        if (response == null || response.getData() == null) {
            throw new RuntimeException("Submit order is failure!");
        }
        entrustOrder.setMargin(response.getData().getMargin());
        entrustOrder.setFeeAmount(response.getData().getFeeAmount());
        generateOrder(entrustOrder);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void generateOrder(EntrustOrder entrustOrder) {
        entrustOrder.setCreateTime(new Date());
        entrustOrder.setCreateBy(SecurityContextHolder.getUserName());
        entrustOrder.setUpdateTime(new Date());
        entrustOrder.setUpdateBy(SecurityContextHolder.getUserName());
        log.info("生成委托订单数据：{}", entrustOrder);
        entrustOrderMapper.insertEntrustOrder(entrustOrder);
        clientUserWalletService.balanceChange(
                entrustOrder.getUserId(),
                entrustOrder.getUserName(),
                entrustOrder.getId(),
                "USD",
                entrustOrder.getMargin().add(entrustOrder.getFeeAmount()),
                ClientConstant.REDUCE
        );
    }
}
