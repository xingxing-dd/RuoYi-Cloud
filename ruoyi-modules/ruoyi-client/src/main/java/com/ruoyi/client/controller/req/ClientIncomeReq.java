package com.ruoyi.client.controller.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ClientIncomeReq implements Serializable {

    private String incomeDate;

    private Long incomeType;

}
