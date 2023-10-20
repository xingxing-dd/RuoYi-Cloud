package com.ruoyi.client.biz.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.client.biz.TradeTransactionService;
import com.ruoyi.client.domain.EntrustOrder;
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.client.service.IEntrustOrderService;
import com.ruoyi.client.service.ITradeOrderService;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanUtils;
import com.ruoyi.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static com.ruoyi.common.core.constant.MarketConstant.BUY;
import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeTransactionServiceImpl implements TradeTransactionService {

    private final RedisService redisService;

    private final ITradeOrderService tradeOrderService;

    private final IEntrustOrderService entrustOrderService;

    private final IClientUserWalletService clientUserWalletService;

    @Override
    public void tradeOrderPriceChange(String productCode) {
        log.info("产品{}价格发生波动，开始检查订单", productCode);
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, DateUtils.dateTime());
        JSONObject price = redisService.getCacheObject(productPriceCacheKey);
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setProductCode(productCode);
        tradeOrder.setStatus(0L);
        List<TradeOrder> tradeOrders = tradeOrderService.selectTradeOrderList(tradeOrder);
        log.info("一共获取到{}条持仓中的订单", CollectionUtils.isEmpty(tradeOrders) ? 0 : tradeOrders.size());
        if (CollectionUtils.isEmpty(tradeOrders)) {
            return;
        }
        tradeOrders.forEach(order -> processTradeOrder(order, price.getBigDecimal("currentPrice")));
    }

    /**
     * 处理交易订单
     * @param tradeOrder
     * @param currentPrice
     */
    @Async
    protected void processTradeOrder(TradeOrder tradeOrder, BigDecimal currentPrice) {
        TradeOrder updateOrder = new TradeOrder();
        updateOrder.setId(tradeOrder.getId());
        updateOrder.setUserId(tradeOrder.getUserId());
        if (StringUtils.equals(BUY, tradeOrder.getTradeDirect())) {
            updateOrder.setIncome(currentPrice.subtract(tradeOrder.getTradePrice()).multiply(tradeOrder.getSheetNum()).setScale(6, RoundingMode.HALF_UP));
        } else {
            updateOrder.setIncome(tradeOrder.getTradePrice().subtract(currentPrice).multiply(tradeOrder.getSheetNum()).setScale(6, RoundingMode.HALF_UP));
        }
        if (updateOrder.getIncome().add(tradeOrder.getMargin()).compareTo(BigDecimal.ZERO) <= 0) {
            updateOrder.setIncome(BigDecimal.ZERO.subtract(tradeOrder.getTradeAmount()));
            updateOrder.setStatus(2L);
            updateOrder.setDeliveryAmount(BigDecimal.ZERO);
            updateOrder.setDeliveryPrice(currentPrice);
            updateOrder.setRemark("强制平仓");
        } else if (tradeOrder.getStopLoss() != null && tradeOrder.getStopLoss().compareTo(BigDecimal.ZERO) > 0 && currentPrice.compareTo(tradeOrder.getStopLoss()) <= 0) {
            updateOrder.setStatus(1L);
            updateOrder.setDeliveryAmount(BigDecimal.ZERO);
            updateOrder.setDeliveryPrice(tradeOrder.getStopLoss());
            updateOrder.setRemark("止损平仓");
        } else if (tradeOrder.getStopProfit() != null && tradeOrder.getStopProfit().compareTo(BigDecimal.ZERO) > 0 && currentPrice.compareTo(tradeOrder.getStopProfit()) >= 0) {
            updateOrder.setStatus(1L);
            updateOrder.setDeliveryAmount(BigDecimal.ZERO);
            updateOrder.setDeliveryPrice(tradeOrder.getStopProfit());
            updateOrder.setRemark("止盈平仓");
        }
        if (tradeOrder.getStatus() != null && tradeOrder.getStatus() != 0L) {
            tradeOrderDelivery(updateOrder);
        }
        updateOrder.setDeliveryTime(new Date());
        updateOrder.setUpdateBy("system");
        updateOrder.setUpdateTime(new Date());
        tradeOrderService.updateTradeOrder(updateOrder);
    }

    private void tradeOrderDelivery(TradeOrder tradeOrder) {
        if (tradeOrder.getIncome() == null || tradeOrder.getIncome().compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        clientUserWalletService.balanceChange(
                tradeOrder.getUserId(),
                "system",
                tradeOrder.getId(),
                "USD",
                tradeOrder.getIncome(),
                ClientConstant.INCREASE
        );
    }

    @Override
    public void entrustOrderPriceChange(String productCode) {
        log.info("产品{}价格发生波动，开始检查订单", productCode);
        String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, productCode, DateUtils.dateTime());
        JSONObject price = redisService.getCacheObject(productPriceCacheKey);
        EntrustOrder entrustOrder = new EntrustOrder();
        entrustOrder.setProductCode(productCode);
        entrustOrder.setStatus(0L);
        List<EntrustOrder> entrustOrders = entrustOrderService.selectEntrustOrderList(entrustOrder);
        log.info("一共获取到{}条待交割的订单", CollectionUtils.isEmpty(entrustOrders) ? 0 : entrustOrders.size());
        if (CollectionUtils.isEmpty(entrustOrders)) {
            return;
        }
        entrustOrders.forEach(order -> processEntrustOrder(order, price.getBigDecimal("currentPrice")));
    }

    protected void processEntrustOrder(EntrustOrder entrustOrder, BigDecimal currentPrice) {
         if (StringUtils.equals(BUY, entrustOrder.getTradeDirect())) {
             processIfBuy(entrustOrder, currentPrice);
         } else {
             processIfSell(entrustOrder, currentPrice);
         }
    }

    protected void processIfBuy(EntrustOrder entrustOrder, BigDecimal currentPrice) {
        if (entrustOrder.getTradePrice().compareTo(currentPrice) < 0) {
            return;
        }
        log.info("委托买入订单{}即将交割", entrustOrder.getOrderId());
        EntrustOrder updateOrder = new EntrustOrder();
        updateOrder.setId(entrustOrder.getId());
        updateOrder.setStatus(1L);
        updateOrder.setUpdateBy("system");
        updateOrder.setUpdateTime(new Date());
        entrustOrderService.updateEntrustOrder(updateOrder);
        generateTradeOrder(entrustOrder);
    }

    protected void processIfSell(EntrustOrder entrustOrder, BigDecimal currentPrice) {
        if (entrustOrder.getTradePrice().compareTo(currentPrice) > 0) {
            return;
        }
        log.info("委托卖出订单{}即将交割", entrustOrder.getOrderId());
        EntrustOrder updateOrder = new EntrustOrder();
        updateOrder.setId(entrustOrder.getId());
        updateOrder.setStatus(1L);
        updateOrder.setUpdateBy("system");
        updateOrder.setUpdateTime(new Date());
        entrustOrderService.updateEntrustOrder(updateOrder);
        generateTradeOrder(entrustOrder);
    }

    protected void generateTradeOrder(EntrustOrder entrustOrder) {
        TradeOrder tradeOrder = new TradeOrder();
        BeanUtils.copyProperties(entrustOrder, tradeOrder);
        tradeOrder.setIncome(BigDecimal.ZERO);
        tradeOrder.setTradeAmount(tradeOrder.getTradePrice().multiply(tradeOrder.getSheetNum()).setScale(2, RoundingMode.HALF_UP));
        tradeOrder.setStatus(0L);
        tradeOrder.setCreateBy("system");
        tradeOrder.setCreateTime(new Date());
        tradeOrder.setUpdateBy("system");
        tradeOrder.setUpdateTime(new Date());
        tradeOrderService.insertTradeOrder(tradeOrder);
    }

}
