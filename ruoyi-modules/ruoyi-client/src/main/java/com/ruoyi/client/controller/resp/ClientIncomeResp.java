package com.ruoyi.client.controller.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ToString
public class ClientIncomeResp implements Serializable {

    private String incomeDate;

    private BigDecimal income;

}
