package com.ruoyi.client.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户资产对象 client_user_wallet
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class ClientUserWallet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    @Excel(name = "用户名")
    private String userName;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalAmount;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 状态(normal/freeze) */
    @Excel(name = "状态(normal/freeze)")
    private String status;

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
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("totalAmount", getTotalAmount())
            .append("currency", getCurrency())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
