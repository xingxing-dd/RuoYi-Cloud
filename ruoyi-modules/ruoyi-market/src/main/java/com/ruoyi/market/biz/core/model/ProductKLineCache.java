package com.ruoyi.market.biz.core.model;


import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class ProductKLineCache implements Serializable {

    /**
     * 时间戳
     */
    private long timestamp;

    /** 产品编号 */
    private String productCode;

    /** 开盘价 */
    private BigDecimal open;

    /** 收盘价 */
    private BigDecimal close;

    /** 最高价 */
    private BigDecimal high;

    /** 最低价 */
    private BigDecimal low;

}
