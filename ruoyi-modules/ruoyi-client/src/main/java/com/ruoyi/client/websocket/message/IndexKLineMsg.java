package com.ruoyi.client.websocket.message;

import com.ruoyi.client.websocket.message.model.KLineNode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class IndexKLineMsg implements Serializable {

    private String productCode;
    /**
     * 最低价
     */
    private double low;

    /**
     * 最高价
     */
    private double high;

    /**
     * 开盘价
     */
    private double open;

    /**
     * 收盘价
     */
    private double close;

    /**
     * 交易量
     */
    private double volume;

    /**
     * 时间戳
     */
    private long timestamp;

}
