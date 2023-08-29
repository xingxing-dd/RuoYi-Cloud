package com.ruoyi.client.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 用户钱包流水对象 client_user_wallet_flow
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public class ClientUserWalletFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 钱包id */
    @Excel(name = "钱包id")
    private Long walletId;

    /** 用户号 */
    @Excel(name = "用户号")
    private Long userId;

    /** 关联订单号 */
    @Excel(name = "关联订单号")
    private Long bizOrderId;

    /** 流水类型 */
    @Excel(name = "流水类型")
    private String type;

    /** 方向(+/-) */
    @Excel(name = "方向(+/-)")
    private String direct;

    /** 变化金额 */
    @Excel(name = "变化金额")
    private BigDecimal amount;

    /** 交易币种 */
    @Excel(name = "交易币种")
    private String currency;

    /** 扩展字段 */
    private String ext;

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
    public void setWalletId(Long walletId) 
    {
        this.walletId = walletId;
    }

    public Long getWalletId() 
    {
        return walletId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBizOrderId(Long bizOrderId) 
    {
        this.bizOrderId = bizOrderId;
    }

    public Long getBizOrderId() 
    {
        return bizOrderId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDirect(String direct) 
    {
        this.direct = direct;
    }

    public String getDirect() 
    {
        return direct;
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
    public void setExt(String ext) 
    {
        this.ext = ext;
    }

    public String getExt() 
    {
        return ext;
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
            .append("walletId", getWalletId())
            .append("userId", getUserId())
            .append("bizOrderId", getBizOrderId())
            .append("type", getType())
            .append("direct", getDirect())
            .append("amount", getAmount())
            .append("currency", getCurrency())
            .append("remark", getRemark())
            .append("ext", getExt())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
