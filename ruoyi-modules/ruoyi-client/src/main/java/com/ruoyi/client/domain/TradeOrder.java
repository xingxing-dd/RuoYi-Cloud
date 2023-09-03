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
 * @date 2023-09-03
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
    private Long magnification;

    /**  */
    @Excel(name = "")
    private BigDecimal margin;

    /** 手续费 */
    @Excel(name = "手续费")
    private Long feeAmount;

    /** 收益 */
    @Excel(name = "收益")
    private BigDecimal income;

    /** 状态(挂单/已成交/已交割) */
    @Excel(name = "状态(挂单/已成交/已交割)")
    private Long status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeAt;

    /** 交割时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交割时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateAt;

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
    public void setMagnification(Long magnification) 
    {
        this.magnification = magnification;
    }

    public Long getMagnification() 
    {
        return magnification;
    }
    public void setMargin(BigDecimal margin) 
    {
        this.margin = margin;
    }

    public BigDecimal getMargin() 
    {
        return margin;
    }
    public void setFeeAmount(Long feeAmount) 
    {
        this.feeAmount = feeAmount;
    }

    public Long getFeeAmount() 
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
    public void setCreateAt(Date createAt) 
    {
        this.createAt = createAt;
    }

    public Date getCreateAt() 
    {
        return createAt;
    }
    public void setCompleteAt(Date completeAt) 
    {
        this.completeAt = completeAt;
    }

    public Date getCompleteAt() 
    {
        return completeAt;
    }
    public void setDeliveryAt(Date deliveryAt) 
    {
        this.deliveryAt = deliveryAt;
    }

    public Date getDeliveryAt() 
    {
        return deliveryAt;
    }
    public void setUpdateAt(Date updateAt) 
    {
        this.updateAt = updateAt;
    }

    public Date getUpdateAt() 
    {
        return updateAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("productCode", getProductCode())
            .append("magnification", getMagnification())
            .append("margin", getMargin())
            .append("feeAmount", getFeeAmount())
            .append("income", getIncome())
            .append("status", getStatus())
            .append("createAt", getCreateAt())
            .append("createBy", getCreateBy())
            .append("completeAt", getCompleteAt())
            .append("deliveryAt", getDeliveryAt())
            .append("updateAt", getUpdateAt())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
