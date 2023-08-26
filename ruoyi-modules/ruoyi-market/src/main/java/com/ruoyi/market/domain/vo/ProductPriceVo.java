package com.ruoyi.market.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductPriceVo {

    /**时间戳*/
    private long timestamp;

    /** 开盘价 */
    private BigDecimal open;

    /** 收盘价 */
    private BigDecimal close;

    /** 最高价 */
    private BigDecimal high;

    /** 最低价 */
    private BigDecimal low;

}
