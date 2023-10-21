package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.client.controller.resp.FinancialOrderResp;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.market.api.RemoteProductInfoService;
import com.ruoyi.market.api.req.FinancialInfoReq;
import com.ruoyi.market.api.resp.FinancialInfoResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.FinancialOrderMapper;
import com.ruoyi.client.domain.FinancialOrder;
import com.ruoyi.client.service.IFinancialOrderService;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private RemoteProductInfoService remoteProductInfoService;

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

    @Override
    public FinancialOrderResp queryFinancialOrder() {
        FinancialOrder params = new FinancialOrder();
        params.setUserId(SecurityContextHolder.getUserId());
        params.setStatus(0L);
        List<FinancialOrder> financialOrders = financialOrderMapper.selectFinancialOrderList(params);
        if (CollectionUtils.isEmpty(financialOrders)) {
            FinancialOrder financialOrder = new FinancialOrder();
            financialOrder.setUserId(SecurityContextHolder.getUserId());
            financialOrder.setUserName(SecurityContextHolder.getUserName());
            financialOrder.setProductCode("YEB");
            financialOrder.setAmount(BigDecimal.ZERO);
            FinancialInfoResp financialInfoResp = getProductInfo();
            if (financialInfoResp == null) {
                financialOrder.setInterestRate(BigDecimal.TEN);
            } else {
                financialOrder.setInterestRate(financialInfoResp.getInterestRate());
            }
            financialOrder.setTotalIncome(BigDecimal.ZERO);
            financialOrder.setYesterdadyIncome(BigDecimal.ZERO);
            financialOrder.setCreateAt(new Date());
            financialOrder.setCreateBy(SecurityContextHolder.getUserName());
            financialOrder.setUpdateAt(new Date());
            financialOrder.setUpdateBy(SecurityContextHolder.getUserName());
            financialOrder.setStatus(0L);
            financialOrderMapper.insertFinancialOrder(financialOrder);
            financialOrders.add(financialOrder);
        }
        FinancialOrderResp financialOrderResp = new FinancialOrderResp();
        BeanUtils.copyProperties(financialOrders.get(0), financialOrderResp);
        return financialOrderResp;
    }

    private FinancialInfoResp getProductInfo() {
        FinancialInfoReq financialInfoReq = new FinancialInfoReq();
        financialInfoReq.setProductCode("YEB");
        financialInfoReq.setStatus(0L);
        R<FinancialInfoResp> result = remoteProductInfoService.queryProduct(financialInfoReq);
        if (result == null || R.isError(result) || result.getData() == null) {
            return null;
        }
        return result.getData();
    }
}
