package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WithdrawAccountInfoResp {

    /** 编号 */
    private Long id;

    /** 账户名 */
    private String accountName;

    /** 账号 */
    private String accountNo;

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

}
