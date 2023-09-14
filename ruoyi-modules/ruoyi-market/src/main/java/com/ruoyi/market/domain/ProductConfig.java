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
 * @date 2023-09-14
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
    private BigDecimal feeRate;

    /** 点差率 */
    @Excel(name = "点差率")
    private BigDecimal spreadRate;

    /** 每张价格 */
    @Excel(name = "每张价格")
    private BigDecimal eachSheetPrice;

    /** 最小张数 */
    @Excel(name = "最小张数")
    private BigDecimal minimumSheet;

    /** 最大张数 */
    @Excel(name = "最大张数")
    private BigDecimal maximunSheet;

    /** 保证金比例 */
    @Excel(name = "保证金比例")
    private BigDecimal marginRate;

    /** 保证金类型 */
    @Excel(name = "保证金类型")
    private String marginType;

    /** 步长 */
    @Excel(name = "步长")
    private BigDecimal stepSize;

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
    public void setFeeRate(BigDecimal feeRate)
    {
        this.feeRate = feeRate;
    }

    public BigDecimal getFeeRate()
    {
        return feeRate;
    }
    public void setSpreadRate(BigDecimal spreadRate)
    {
        this.spreadRate = spreadRate;
    }

    public BigDecimal getSpreadRate()
    {
        return spreadRate;
    }
    public void setEachSheetPrice(BigDecimal eachSheetPrice)
    {
        this.eachSheetPrice = eachSheetPrice;
    }

    public BigDecimal getEachSheetPrice()
    {
        return eachSheetPrice;
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
    public void setMarginRate(BigDecimal marginRate)
    {
        this.marginRate = marginRate;
    }

    public BigDecimal getMarginRate()
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
    public void setStepSize(BigDecimal stepSize)
    {
        this.stepSize = stepSize;
    }

    public BigDecimal getStepSize()
    {
        return stepSize;
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
                .append("spreadRate", getSpreadRate())
                .append("eachSheetPrice", getEachSheetPrice())
                .append("minimumSheet", getMinimumSheet())
                .append("maximunSheet", getMaximunSheet())
                .append("marginRate", getMarginRate())
                .append("marginType", getMarginType())
                .append("stepSize", getStepSize())
                .append("tradePeriod", getTradePeriod())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
