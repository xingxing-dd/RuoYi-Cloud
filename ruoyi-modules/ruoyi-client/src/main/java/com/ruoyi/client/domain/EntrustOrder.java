package com.ruoyi.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 委托订单对象 entrust_order
 *
 * @author ruoyi
 * @date 2023-10-07
 */
public class EntrustOrder extends BaseEntity
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

    /** 张数 */
    @Excel(name = "张数")
    private BigDecimal sheetNum;

    /** 交易方向 */
    @Excel(name = "交易方向")
    private String tradeDirect;

    /** 交易价格 */
    @Excel(name = "交易价格")
    private BigDecimal tradePrice;

    /** 止盈点 */
    @Excel(name = "止盈点")
    private BigDecimal stopProfit;

    /** 止损点 */
    @Excel(name = "止损点")
    private BigDecimal stopLoss;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal feeAmount;

    /** 状态(委托中/已成交，已取消) */
    @Excel(name = "状态(委托中/已成交，已取消)")
    private Long status;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completedTime;

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
    public void setStopProfit(BigDecimal stopProfit)
    {
        this.stopProfit = stopProfit;
    }

    public BigDecimal getStopProfit()
    {
        return stopProfit;
    }
    public void setStopLoss(BigDecimal stopLoss)
    {
        this.stopLoss = stopLoss;
    }

    public BigDecimal getStopLoss()
    {
        return stopLoss;
    }
    public void setFeeAmount(BigDecimal feeAmount)
    {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeeAmount()
    {
        return feeAmount;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setCompletedTime(Date completedTime)
    {
        this.completedTime = completedTime;
    }

    public Date getCompletedTime()
    {
        return completedTime;
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
                .append("sheetNum", getSheetNum())
                .append("tradeDirect", getTradeDirect())
                .append("tradePrice", getTradePrice())
                .append("stopProfit", getStopProfit())
                .append("stopLoss", getStopLoss())
                .append("feeAmount", getFeeAmount())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("completedTime", getCompletedTime())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
