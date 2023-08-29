package com.ruoyi.client.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 充值银行信息对象 recharge_bank
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class RechargeBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 银行地址 */
    @Excel(name = "银行地址")
    private String bankAddress;

    /** swift */
    @Excel(name = "swift")
    private String swift;

    /** 收款人名称 */
    @Excel(name = "收款人名称")
    private String payeeName;

    /** 收款人账号 */
    @Excel(name = "收款人账号")
    private String payeeAcct;

    /** 收款币种 */
    @Excel(name = "收款币种")
    private String payeeCurrency;

    /** 是否展示 */
    @Excel(name = "是否展示")
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
    public void setBankName(String bankName) 
    {
        this.bankName = bankName;
    }

    public String getBankName() 
    {
        return bankName;
    }
    public void setBankAddress(String bankAddress) 
    {
        this.bankAddress = bankAddress;
    }

    public String getBankAddress() 
    {
        return bankAddress;
    }
    public void setSwift(String swift) 
    {
        this.swift = swift;
    }

    public String getSwift() 
    {
        return swift;
    }
    public void setPayeeName(String payeeName) 
    {
        this.payeeName = payeeName;
    }

    public String getPayeeName() 
    {
        return payeeName;
    }
    public void setPayeeAcct(String payeeAcct) 
    {
        this.payeeAcct = payeeAcct;
    }

    public String getPayeeAcct() 
    {
        return payeeAcct;
    }
    public void setPayeeCurrency(String payeeCurrency) 
    {
        this.payeeCurrency = payeeCurrency;
    }

    public String getPayeeCurrency() 
    {
        return payeeCurrency;
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
            .append("bankName", getBankName())
            .append("bankAddress", getBankAddress())
            .append("swift", getSwift())
            .append("payeeName", getPayeeName())
            .append("payeeAcct", getPayeeAcct())
            .append("payeeCurrency", getPayeeCurrency())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
