package com.ruoyi.market.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 大盘价格数据源对象 mk_product_price
 * 
 * @author ruoyi
 * @date 2023-08-28
 */
public class ProductPrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 开盘价 */
    @Excel(name = "开盘价")
    private BigDecimal open;

    /** 收盘价 */
    @Excel(name = "收盘价")
    private BigDecimal close;

    /** 最高价 */
    @Excel(name = "最高价")
    private BigDecimal high;

    /** 最低价 */
    @Excel(name = "最低价")
    private BigDecimal low;

    /** 变化幅度 */
    @Excel(name = "变化幅度")
    private BigDecimal range;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTimestamp(Date timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() 
    {
        return timestamp;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setOpen(BigDecimal open) 
    {
        this.open = open;
    }

    public BigDecimal getOpen() 
    {
        return open;
    }
    public void setClose(BigDecimal close) 
    {
        this.close = close;
    }

    public BigDecimal getClose() 
    {
        return close;
    }
    public void setHigh(BigDecimal high) 
    {
        this.high = high;
    }

    public BigDecimal getHigh() 
    {
        return high;
    }
    public void setLow(BigDecimal low) 
    {
        this.low = low;
    }

    public BigDecimal getLow() 
    {
        return low;
    }
    public void setRange(BigDecimal range) 
    {
        this.range = range;
    }

    public BigDecimal getRange() 
    {
        return range;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("timestamp", getTimestamp())
            .append("productCode", getProductCode())
            .append("open", getOpen())
            .append("close", getClose())
            .append("high", getHigh())
            .append("low", getLow())
            .append("range", getRange())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
