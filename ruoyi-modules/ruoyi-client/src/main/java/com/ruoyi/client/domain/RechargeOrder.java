package com.ruoyi.client.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 充值订单对象 recharge_order
 * 
 * @author ruoyi
 * @date 2023-08-29cd ..
 */
public class RechargeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 充值方式 */
    @Excel(name = "充值方式")
    private String rechargeType;

    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal rechargeAmount;

    /** 充值币种 */
    @Excel(name = "充值币种")
    private String rechargeCurrency;

    /** 到账金额 */
    @Excel(name = "到账金额")
    private BigDecimal receiveAmount;

    /** 到账币种 */
    @Excel(name = "到账币种")
    private String receiveCurrency;

    /** 充值凭证 */
    @Excel(name = "充值凭证")
    private String rechargeInvoice;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long status;

    /** 删除标识 */
    private Long delFlag;

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
    public void setRechargeType(String rechargeType) 
    {
        this.rechargeType = rechargeType;
    }

    public String getRechargeType() 
    {
        return rechargeType;
    }
    public void setRechargeAmount(BigDecimal rechargeAmount) 
    {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getRechargeAmount() 
    {
        return rechargeAmount;
    }
    public void setRechargeCurrency(String rechargeCurrency) 
    {
        this.rechargeCurrency = rechargeCurrency;
    }

    public String getRechargeCurrency() 
    {
        return rechargeCurrency;
    }
    public void setReceiveAmount(BigDecimal receiveAmount) 
    {
        this.receiveAmount = receiveAmount;
    }

    public BigDecimal getReceiveAmount() 
    {
        return receiveAmount;
    }
    public void setReceiveCurrency(String receiveCurrency) 
    {
        this.receiveCurrency = receiveCurrency;
    }

    public String getReceiveCurrency() 
    {
        return receiveCurrency;
    }
    public void setRechargeInvoice(String rechargeInvoice) 
    {
        this.rechargeInvoice = rechargeInvoice;
    }

    public String getRechargeInvoice() 
    {
        return rechargeInvoice;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("rechargeType", getRechargeType())
            .append("rechargeAmount", getRechargeAmount())
            .append("rechargeCurrency", getRechargeCurrency())
            .append("receiveAmount", getReceiveAmount())
            .append("receiveCurrency", getReceiveCurrency())
            .append("rechargeInvoice", getRechargeInvoice())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
