package com.ruoyi.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 余额宝订单对象 financial_order
 *
 * @author ruoyi
 * @date 2023-10-20
 */
public class FinancialOrder extends BaseEntity
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

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 年化利率 */
    @Excel(name = "年化利率")
    private BigDecimal interestRate;

    /** 昨日收益 */
    @Excel(name = "昨日收益")
    private BigDecimal yesterdadyIncome;

    /** 总收益 */
    @Excel(name = "总收益")
    private BigDecimal totalIncome;

    /** 扩展字段 */
    @Excel(name = "扩展字段")
    private String ext;

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
    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setInterestRate(BigDecimal interestRate)
    {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate()
    {
        return interestRate;
    }
    public void setYesterdadyIncome(BigDecimal yesterdadyIncome)
    {
        this.yesterdadyIncome = yesterdadyIncome;
    }

    public BigDecimal getYesterdadyIncome()
    {
        return yesterdadyIncome;
    }
    public void setTotalIncome(BigDecimal totalIncome)
    {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalIncome()
    {
        return totalIncome;
    }
    public void setExt(String ext)
    {
        this.ext = ext;
    }

    public String getExt()
    {
        return ext;
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
                .append("productCode", getProductCode())
                .append("amount", getAmount())
                .append("interestRate", getInterestRate())
                .append("yesterdadyIncome", getYesterdadyIncome())
                .append("totalIncome", getTotalIncome())
                .append("remark", getRemark())
                .append("ext", getExt())
                .append("status", getStatus())
                .append("createAt", getCreateAt())
                .append("createBy", getCreateBy())
                .append("updateAt", getUpdateAt())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
