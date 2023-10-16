package com.ruoyi.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 交易订单对象 trade_order
 *
 * @author ruoyi
 * @date 2023-10-07
 */
public class TradeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 倍率 */
    @Excel(name = "倍率")
    private BigDecimal multiplier;

    /** 保证金 */
    @Excel(name = "保证金")
    private BigDecimal margin;

    /** 止损点 */
    @Excel(name = "止损点")
    private BigDecimal stopLoss;

    /** 止盈点 */
    @Excel(name = "止盈点")
    private BigDecimal stopProfit;

    /** 张数 */
    @Excel(name = "张数")
    private BigDecimal sheetNum;

    /** 交易方向 */
    @Excel(name = "交易方向")
    private String tradeDirect;

    /** 买入价格 */
    @Excel(name = "买入价格")
    private BigDecimal tradePrice;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private BigDecimal tradeAmount;

    /** 卖出价格 */
    @Excel(name = "卖出价格")
    private BigDecimal deliveryPrice;

    /** 交割金额 */
    @Excel(name = "交割金额")
    private BigDecimal deliveryAmount;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal feeAmount;

    /** 收益 */
    @Excel(name = "收益")
    private BigDecimal income;

    /** 状态(已成交/已交割/强制平仓) */
    @Excel(name = "状态(已成交/已交割/强制平仓)")
    private Long status;

    /** 交割时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交割时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }
    public void setMultiplier(BigDecimal multiplier)
    {
        this.multiplier = multiplier;
    }

    public BigDecimal getMultiplier()
    {
        return multiplier;
    }
    public void setMargin(BigDecimal margin)
    {
        this.margin = margin;
    }

    public BigDecimal getMargin()
    {
        return margin;
    }
    public void setStopLoss(BigDecimal stopLoss)
    {
        this.stopLoss = stopLoss;
    }

    public BigDecimal getStopLoss()
    {
        return stopLoss;
    }
    public void setStopProfit(BigDecimal stopProfit)
    {
        this.stopProfit = stopProfit;
    }

    public BigDecimal getStopProfit()
    {
        return stopProfit;
    }
    public void setSheetNum(BigDecimal sheetNum)
    {
        this.sheetNum = sheetNum;
    }

    public BigDecimal getSheetNum()
    {
        return sheetNum;
    }
    public void setTradeDirect(String tradeDirect)
    {
        this.tradeDirect = tradeDirect;
    }

    public String getTradeDirect()
    {
        return tradeDirect;
    }
    public void setTradePrice(BigDecimal tradePrice)
    {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getTradePrice()
    {
        return tradePrice;
    }
    public void setTradeAmount(BigDecimal tradeAmount)
    {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getTradeAmount()
    {
        return tradeAmount;
    }
    public void setDeliveryPrice(BigDecimal deliveryPrice)
    {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getDeliveryPrice()
    {
        return deliveryPrice;
    }
    public void setDeliveryAmount(BigDecimal deliveryAmount)
    {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getDeliveryAmount()
    {
        return deliveryAmount;
    }
    public void setFeeAmount(BigDecimal feeAmount)
    {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeeAmount()
    {
        return feeAmount;
    }
    public void setIncome(BigDecimal income)
    {
        this.income = income;
    }

    public BigDecimal getIncome()
    {
        return income;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setDeliveryTime(Date deliveryTime)
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime()
    {
        return deliveryTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("productCode", getProductCode())
                .append("multiplier", getMultiplier())
                .append("margin", getMargin())
                .append("stopLoss", getStopLoss())
                .append("stopProfit", getStopProfit())
                .append("sheetNum", getSheetNum())
                .append("tradeDirect", getTradeDirect())
                .append("tradePrice", getTradePrice())
                .append("tradeAmount", getTradeAmount())
                .append("deliveryPrice", getDeliveryPrice())
                .append("deliveryAmount", getDeliveryAmount())
                .append("feeAmount", getFeeAmount())
                .append("income", getIncome())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("deliveryTime", getDeliveryTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
