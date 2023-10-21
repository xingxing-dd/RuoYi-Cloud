package com.ruoyi.market.api.req;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 理财产品对象 mk_financial_info
 * 
 * @author ruoyi
 * @date 2023-10-19
 */
public class FinancialInfoReq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品图标 */
    @Excel(name = "产品图标")
    private String productIcon;

    /** 产品描述 */
    @Excel(name = "产品描述")
    private String productDesc;

    /** 年化利率 */
    @Excel(name = "年化利率")
    private BigDecimal interestRate;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 备用字段 */
    private String ext;

    /** 创建时间 */
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
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductIcon(String productIcon) 
    {
        this.productIcon = productIcon;
    }

    public String getProductIcon() 
    {
        return productIcon;
    }
    public void setProductDesc(String productDesc) 
    {
        this.productDesc = productDesc;
    }

    public String getProductDesc() 
    {
        return productDesc;
    }
    public void setInterestRate(BigDecimal interestRate) 
    {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() 
    {
        return interestRate;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setExt(String ext) 
    {
        this.ext = ext;
    }

    public String getExt() 
    {
        return ext;
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
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("productIcon", getProductIcon())
            .append("productDesc", getProductDesc())
            .append("interestRate", getInterestRate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("ext", getExt())
            .append("createAt", getCreateAt())
            .append("createBy", getCreateBy())
            .append("updateAt", getUpdateAt())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
