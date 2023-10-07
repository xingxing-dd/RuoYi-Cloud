package com.ruoyi.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 提现订单对象 withdraw_order
 * 
 * @author ruoyi
 * @date 2023-10-05
 */
public class WithdrawOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal amount;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 提现账号 */
    @Excel(name = "提现账号")
    private String fundAcct;

    /** 手续费 */
    @Excel(name = "手续费")
    private BigDecimal feeAmount;

    /** 到账金额 */
    @Excel(name = "到账金额")
    private BigDecimal receivedAmount;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createAt;

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
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setFundAcct(String fundAcct) 
    {
        this.fundAcct = fundAcct;
    }

    public String getFundAcct() 
    {
        return fundAcct;
    }
    public void setFeeAmount(BigDecimal feeAmount) 
    {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getFeeAmount() 
    {
        return feeAmount;
    }
    public void setReceivedAmount(BigDecimal receivedAmount) 
    {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getReceivedAmount() 
    {
        return receivedAmount;
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
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("orderId", getOrderId())
            .append("amount", getAmount())
            .append("currency", getCurrency())
            .append("fundAcct", getFundAcct())
            .append("feeAmount", getFeeAmount())
            .append("receivedAmount", getReceivedAmount())
            .append("status", getStatus())
            .append("createAt", getCreateAt())
            .append("createBy", getCreateBy())
            .append("updateAt", getUpdateAt())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
