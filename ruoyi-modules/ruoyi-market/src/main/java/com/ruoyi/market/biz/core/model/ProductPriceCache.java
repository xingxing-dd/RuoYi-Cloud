package com.ruoyi.market.biz.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class ProductPriceCache implements Serializable {

    private String productCode;

    private Date currentTime;

    private BigDecimal openPrice;

    private BigDecimal currentPrice;

    private BigDecimal range;

    public boolean hasChange(Date currentTime, BigDecimal currentPrice) {
        if (this.currentTime.compareTo(currentTime) != 0) {
            return true;
        }
        if (this.currentPrice.compareTo(currentPrice) != 0) {
            return true;
        }
        return false;
    }

}
