package com.ruoyi.client.job;

import com.ruoyi.client.domain.FinancialOrder;
import com.ruoyi.client.service.IClientIncomeService;
import com.ruoyi.client.service.IFinancialOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class FinancialOrderJob {

    @Autowired
    private IFinancialOrderService financialOrderService;

    @Autowired
    private IClientIncomeService clientIncomeService;

    @Scheduled(cron = "0 0 1 * * *")
    public void process() {
        log.info("开始处理余额宝订单");
        FinancialOrder financialOrder = new FinancialOrder();
        financialOrder.setStatus(0L);
        financialOrder.setProductCode("YEB");
        List<FinancialOrder> financialOrders = financialOrderService.selectFinancialOrderList(financialOrder);
        log.info("一共获取到{}条余额宝订单", CollectionUtils.isEmpty(financialOrders) ? 0 : financialOrders.size());
        if (CollectionUtils.isNotEmpty(financialOrders)) {
            financialOrders.forEach(this::doProcess);
        }
        log.info("结束处理余额宝订单");
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void doProcess(FinancialOrder financialOrder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        FinancialOrder updateOrder = new FinancialOrder();
        updateOrder.setId(financialOrder.getId());
        BigDecimal yesterdayIncome = financialOrder.getAmount().multiply(financialOrder.getInterestRate()).divide(new BigDecimal(365), 2, RoundingMode.HALF_UP);
        if (yesterdayIncome.compareTo(new BigDecimal(0.01)) < 0) {
            yesterdayIncome = new BigDecimal(0.01);
        }
        updateOrder.setTotalIncome(financialOrder.getAmount().add(yesterdayIncome));
        updateOrder.setYesterdayIncome(yesterdayIncome);
        updateOrder.setTotalIncome(financialOrder.getTotalIncome() == null ? yesterdayIncome : financialOrder.getTotalIncome().add(yesterdayIncome));
        updateOrder.setUpdateAt(new Date());
        updateOrder.setUpdateBy("sysdate");
        financialOrderService.updateFinancialOrder(updateOrder);
        //生成余额宝收益
        clientIncomeService.createUserIncome(
                financialOrder.getUserId(),
                financialOrder.getUserName(),
                1L,
                yesterdayIncome,
                dateFormat.format(DateUtils.addDays(new Date(), -1)),

                "余额宝收益"
        );
    }

}
