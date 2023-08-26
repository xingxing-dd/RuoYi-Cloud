package com.ruoyi.market.websocket.message;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class WsPriceMessage implements Serializable {

    /** 产品编码 */
    private String productCode;

    /** 时间戳 */
    private long timestamp;

    /** 当前价格 */
    private BigDecimal currentPrice;

    /** 涨跌幅 */
    private BigDecimal range;

    /** 开盘价 */
    private BigDecimal open;

    /** 收盘价 */
    private BigDecimal close;

    /** 最高价 */
    private BigDecimal high;

    /** 最低价 */
    private BigDecimal low;

}
