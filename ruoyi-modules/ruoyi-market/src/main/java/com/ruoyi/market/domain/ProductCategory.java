package com.ruoyi.market.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 产品类别对象 mk_product_category
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public class ProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别编号 */
    private Long id;

    /** 类别编号 */
    @Excel(name = "类别编号")
    private String categoryCode;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 扩展字段 */
    @Excel(name = "扩展字段")
    private String ext;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("ext", getExt())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
