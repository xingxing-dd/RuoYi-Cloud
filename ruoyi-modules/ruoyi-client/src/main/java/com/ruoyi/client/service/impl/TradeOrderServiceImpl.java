package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.client.controller.req.ProductPriceCache;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.uuid.UUID;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.market.api.RemoteProductInfoService;
import com.ruoyi.market.api.req.ExchangeOrderCalculateReq;
import com.ruoyi.market.api.resp.ExchangeOrderCalculateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.TradeOrderMapper;
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.ITradeOrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.ruoyi.common.core.constant.MarketConstant.*;

/**
 * 交易订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
@Slf4j
@Service
public class TradeOrderServiceImpl implements ITradeOrderService
{
    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private RemoteProductInfoService remoteProductInfoService;

    @Autowired
    private IClientUserWalletService clientUserWalletService;

    @Resource
    private RedisService redisService;

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

    @Override
    public boolean submit(TradeOrder tradeOrder) {
        String orderId = UUID.fastUUID().toString().replaceAll("[-_]", "");
        log.info("生成交易订单id：{}", orderId);
        tradeOrder.setOrderId(orderId);
        tradeOrder.setUserId(SecurityContextHolder.getUserId());
        tradeOrder.setUserName(SecurityContextHolder.getUserName());
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, tradeOrder.getProductCode(), DateUtils.dateTime());
        JSONObject productPriceCache = redisService.getCacheObject(productPriceCacheKey);
        if (productPriceCache == null) {
            throw new RuntimeException("Submit order is failure!");
        }
        ExchangeOrderCalculateReq calculateReq = new ExchangeOrderCalculateReq();
        calculateReq.setProductCode(tradeOrder.getProductCode());
        calculateReq.setDirect(tradeOrder.getTradeDirect());
        calculateReq.setMultiplier(tradeOrder.getMultiplier());
        calculateReq.setExchangePrice(productPriceCache.getBigDecimal("currentPrice"));
        calculateReq.setSheetNum(tradeOrder.getSheetNum());
        R<ExchangeOrderCalculateVo> response = remoteProductInfoService.calculate(calculateReq);
        if (response == null || response.getData() == null) {
            throw new RuntimeException("Submit order is failure!");
        }
        tradeOrder.setTradeAmount(response.getData().getExchangeAmount());
        tradeOrder.setTradePrice(productPriceCache.getBigDecimal("currentPrice"));
        tradeOrder.setMargin(response.getData().getMargin());
        tradeOrder.setFeeAmount(response.getData().getFeeAmount());
        generateOrder(tradeOrder);
        return true;
    }

    @Override
    public boolean sellOut(TradeOrder tradeOrder) {
        TradeOrder existOrder = tradeOrderMapper.selectTradeOrderById(tradeOrder.getId());
        if (existOrder == null) {
            throw new IllegalArgumentException("Order is not exist");
        }
        if (!ClientConstant.POSITION_PENDING.equals(existOrder.getStatus())) {
            throw new IllegalArgumentException("Order status is not correct");
        }
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, existOrder.getProductCode(), DateUtils.dateTime());
        JSONObject price = redisService.getCacheObject(productPriceCacheKey);
        BigDecimal currentPrice = price.getBigDecimal("currentPrice");
        TradeOrder updateOrder = new TradeOrder();
        if (StringUtils.equals(BUY, tradeOrder.getTradeDirect())) {
            updateOrder.setIncome(currentPrice.subtract(existOrder.getTradePrice()).multiply(existOrder.getSheetNum()).setScale(6, RoundingMode.HALF_UP));
        } else {
            updateOrder.setIncome(existOrder.getTradePrice().subtract(currentPrice).multiply(existOrder.getSheetNum()).setScale(6, RoundingMode.HALF_UP));
        }
        updateOrder.setStatus(1L);
        updateOrder.setDeliveryAmount(currentPrice.multiply(existOrder.getSheetNum()).setScale(6, RoundingMode.HALF_UP));
        updateOrder.setDeliveryPrice(currentPrice);
        updateOrder.setRemark("止盈平仓");
        updateTradeOrder(updateOrder);
        clientUserWalletService.balanceChange(
                existOrder.getUserId(),
                "system",
                updateOrder.getId(),
                "USD",
                updateOrder.getIncome(),
                ClientConstant.INCREASE
        );
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public void generateOrder(TradeOrder tradeOrder) {
        tradeOrder.setStatus(0L);
        tradeOrder.setCreateTime(new Date());
        tradeOrder.setCreateBy(SecurityContextHolder.getUserName());
        tradeOrder.setUpdateTime(new Date());
        tradeOrder.setUpdateBy(SecurityContextHolder.getUserName());
        log.info("生成转账订单数据：{}", tradeOrder);
        tradeOrderMapper.insertTradeOrder(tradeOrder);
        clientUserWalletService.balanceChange(
                tradeOrder.getUserId(),
                tradeOrder.getUserName(),
                tradeOrder.getId(),
                "USD",
                tradeOrder.getMargin().add(tradeOrder.getFeeAmount()),
                ClientConstant.REDUCE
        );
    }

}
