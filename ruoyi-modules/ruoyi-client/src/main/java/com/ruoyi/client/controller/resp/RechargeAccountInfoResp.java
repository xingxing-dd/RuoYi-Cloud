package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RechargeAccountInfoResp implements Serializable {

    /** 账户名称*/
    private String accountName;

    /** 账号 */
    private String accountNo;

    /** 账号二维码*/
    private String accountNoQrCode;

    /** 币种 */
    private String accountCurrency;

    /** 账户类型 */
    private String accountType;

    /** 账户用途 */
    private String accountUsage;

    /** 账户所属机构 */
    private String accountOwner;

    /** 账号机构号码 */
    private String accountOwnerCode;

    /** 账号机构地址 */
    private String accountOwnerAddr;

    private String remark;

}
