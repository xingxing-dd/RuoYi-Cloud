package com.ruoyi.client.controller.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FundAccountInfoReq implements Serializable {

    private String accountType;

    private String accountCurrency;

    private String accountOwnerCode;

}
