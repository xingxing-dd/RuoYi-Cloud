package com.ruoyi.market.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 产品配置对象 mk_product_config
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public class ProductConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 交易费率 */
    @Excel(name = "交易费率")
    private Long feeRate;

    /** 最小张数 */
    @Excel(name = "最小张数")
    private BigDecimal minimumSheet;

    /** 最大张数 */
    @Excel(name = "最大张数")
    private BigDecimal maximunSheet;

    /** 保证金比例 */
    @Excel(name = "保证金比例")
    private Long marginRate;

    /** 保证金类型 */
    @Excel(name = "保证金类型")
    private String marginType;

    /** 开盘时间 */
    @Excel(name = "开盘时间")
    private String tradePeriod;

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
    public void setFeeRate(Long feeRate) 
    {
        this.feeRate = feeRate;
    }

    public Long getFeeRate() 
    {
        return feeRate;
    }
    public void setMinimumSheet(BigDecimal minimumSheet) 
    {
        this.minimumSheet = minimumSheet;
    }

    public BigDecimal getMinimumSheet() 
    {
        return minimumSheet;
    }
    public void setMaximunSheet(BigDecimal maximunSheet) 
    {
        this.maximunSheet = maximunSheet;
    }

    public BigDecimal getMaximunSheet() 
    {
        return maximunSheet;
    }
    public void setMarginRate(Long marginRate) 
    {
        this.marginRate = marginRate;
    }

    public Long getMarginRate() 
    {
        return marginRate;
    }
    public void setMarginType(String marginType) 
    {
        this.marginType = marginType;
    }

    public String getMarginType() 
    {
        return marginType;
    }
    public void setTradePeriod(String tradePeriod) 
    {
        this.tradePeriod = tradePeriod;
    }

    public String getTradePeriod() 
    {
        return tradePeriod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productCode", getProductCode())
            .append("feeRate", getFeeRate())
            .append("minimumSheet", getMinimumSheet())
            .append("maximunSheet", getMaximunSheet())
            .append("marginRate", getMarginRate())
            .append("marginType", getMarginType())
            .append("tradePeriod", getTradePeriod())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
