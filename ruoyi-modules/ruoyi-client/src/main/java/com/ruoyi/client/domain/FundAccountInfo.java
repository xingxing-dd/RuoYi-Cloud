package com.ruoyi.client.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 账号管理对象 fund_account_info
 * 
 * @author ruoyi
 * @date 2023-08-31
 */
public class FundAccountInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 所属用户名 */
    @Excel(name = "所属用户名")
    private String userName;

    /** 账户名 */
    @Excel(name = "账户名")
    private String accountName;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNo;

    /** 币种 */
    @Excel(name = "币种")
    private String accountCurrency;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private String accountType;

    /** 账户用途 */
    @Excel(name = "账户用途")
    private String accountUsage;

    /** 账户所属机构 */
    @Excel(name = "账户所属机构")
    private String accountOwner;

    /** 账号机构号码 */
    @Excel(name = "账号机构号码")
    private String accountOwnerCode;

    /** 账号机构地址 */
    @Excel(name = "账号机构地址")
    private String accountOwnerAddr;

    /** 状态 */
    @Excel(name = "状态")
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
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }

    public String getAccountNo() 
    {
        return accountNo;
    }
    public void setAccountCurrency(String accountCurrency) 
    {
        this.accountCurrency = accountCurrency;
    }

    public String getAccountCurrency() 
    {
        return accountCurrency;
    }
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }

    public String getAccountType() 
    {
        return accountType;
    }
    public void setAccountUsage(String accountUsage) 
    {
        this.accountUsage = accountUsage;
    }

    public String getAccountUsage() 
    {
        return accountUsage;
    }
    public void setAccountOwner(String accountOwner) 
    {
        this.accountOwner = accountOwner;
    }

    public String getAccountOwner() 
    {
        return accountOwner;
    }
    public void setAccountOwnerCode(String accountOwnerCode) 
    {
        this.accountOwnerCode = accountOwnerCode;
    }

    public String getAccountOwnerCode() 
    {
        return accountOwnerCode;
    }
    public void setAccountOwnerAddr(String accountOwnerAddr) 
    {
        this.accountOwnerAddr = accountOwnerAddr;
    }

    public String getAccountOwnerAddr() 
    {
        return accountOwnerAddr;
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
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("accountName", getAccountName())
            .append("accountNo", getAccountNo())
            .append("accountCurrency", getAccountCurrency())
            .append("accountType", getAccountType())
            .append("accountUsage", getAccountUsage())
            .append("accountOwner", getAccountOwner())
            .append("accountOwnerCode", getAccountOwnerCode())
            .append("accountOwnerAddr", getAccountOwnerAddr())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
