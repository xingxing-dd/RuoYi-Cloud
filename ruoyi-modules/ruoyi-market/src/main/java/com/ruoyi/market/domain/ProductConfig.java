package com.ruoyi.market.domain;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 产品配置对象 mk_product_config
 *
 * @author ruoyi
 * @date 2023-10-06
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

    /** 每张数量 */
    @Excel(name = "每张数量")
    private Double eachSheetNum;

    /** 最小倍率 */
    @Excel(name = "最小倍率")
    private Double minMultiplier;

    /** 最大倍率 */
    @Excel(name = "最大倍率")
    private Double maxMultiplier;

    /** 倍率步长 */
    @Excel(name = "倍率步长")
    private Double multiplierStepSize;

    /** 最小张数 */
    @Excel(name = "最小张数")
    private Double minSheetNum;

    /** 最大张数 */
    @Excel(name = "最大张数")
    private Double maxSheetNum;

    /** 张数步长 */
    @Excel(name = "张数步长")
    private Double sheetStepSize;

    /** 保证金 */
    @Excel(name = "保证金")
    private BigDecimal marginValue;

    /** 保证金类型 */
    @Excel(name = "保证金类型")
    private String marginType;

    /** 价格步长 */
    @Excel(name = "价格步长")
    private Double priceStepSize;

    /** 价格小数位 */
    @Excel(name = "价格小数位")
    private Integer priceUnit;

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
    public void setEachSheetNum(Double eachSheetNum)
    {
        this.eachSheetNum = eachSheetNum;
    }

    public Double getEachSheetNum()
    {
        return eachSheetNum;
    }
    public void setMinMultiplier(Double minMultiplier)
    {
        this.minMultiplier = minMultiplier;
    }

    public Double getMinMultiplier()
    {
        return minMultiplier;
    }
    public void setMaxMultiplier(Double maxMultiplier)
    {
        this.maxMultiplier = maxMultiplier;
    }

    public Double getMaxMultiplier()
    {
        return maxMultiplier;
    }
    public void setMultiplierStepSize(Double multiplierStepSize)
    {
        this.multiplierStepSize = multiplierStepSize;
    }

    public Double getMultiplierStepSize()
    {
        return multiplierStepSize;
    }
    public void setMinSheetNum(Double minSheetNum)
    {
        this.minSheetNum = minSheetNum;
    }

    public Double getMinSheetNum()
    {
        return minSheetNum;
    }
    public void setMaxSheetNum(Double maxSheetNum)
    {
        this.maxSheetNum = maxSheetNum;
    }

    public Double getMaxSheetNum()
    {
        return maxSheetNum;
    }
    public void setSheetStepSize(Double sheetStepSize)
    {
        this.sheetStepSize = sheetStepSize;
    }

    public Double getSheetStepSize()
    {
        return sheetStepSize;
    }
    public void setMarginValue(BigDecimal marginValue)
    {
        this.marginValue = marginValue;
    }

    public BigDecimal getMarginValue()
    {
        return marginValue;
    }
    public void setMarginType(String marginType)
    {
        this.marginType = marginType;
    }

    public String getMarginType()
    {
        return marginType;
    }
    public void setPriceStepSize(Double priceStepSize)
    {
        this.priceStepSize = priceStepSize;
    }

    public Double getPriceStepSize()
    {
        return priceStepSize;
    }
    public void setPriceUnit(Integer priceUnit)
    {
        this.priceUnit = priceUnit;
    }

    public Integer getPriceUnit()
    {
        return priceUnit;
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
                .append("eachSheetNum", getEachSheetNum())
                .append("minMultiplier", getMinMultiplier())
                .append("maxMultiplier", getMaxMultiplier())
                .append("multiplierStepSize", getMultiplierStepSize())
                .append("minSheetNum", getMinSheetNum())
                .append("maxSheetNum", getMaxSheetNum())
                .append("sheetStepSize", getSheetStepSize())
                .append("marginValue", getMarginValue())
                .append("marginType", getMarginType())
                .append("priceStepSize", getPriceStepSize())
                .append("priceUnit", getPriceUnit())
                .append("tradePeriod", getTradePeriod())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
