package com.ruoyi.client.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 实名认证对象 client_user_auth
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
public class ClientUserAuth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 证件正面 */
    @Excel(name = "证件正面")
    private String credentialFront;

    /** 证件反面 */
    @Excel(name = "证件反面")
    private String credentialBackground;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 证件号 */
    @Excel(name = "证件号")
    private String credentialNo;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

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
    public void setCredentialFront(String credentialFront) 
    {
        this.credentialFront = credentialFront;
    }

    public String getCredentialFront() 
    {
        return credentialFront;
    }
    public void setCredentialBackground(String credentialBackground) 
    {
        this.credentialBackground = credentialBackground;
    }

    public String getCredentialBackground() 
    {
        return credentialBackground;
    }
    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }
    public void setCredentialNo(String credentialNo) 
    {
        this.credentialNo = credentialNo;
    }

    public String getCredentialNo() 
    {
        return credentialNo;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("credentialFront", getCredentialFront())
            .append("credentialBackground", getCredentialBackground())
            .append("realName", getRealName())
            .append("credentialNo", getCredentialNo())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
