package com.ruoyi.client.console;

import lombok.Getter;

@Getter
public enum FundAccountInfoTypeEnum {

    WITHDRAW("withdraw", "提现"),
    RECHARGE("recharge","充值");

    private String code;

    private String desc;

    FundAccountInfoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
