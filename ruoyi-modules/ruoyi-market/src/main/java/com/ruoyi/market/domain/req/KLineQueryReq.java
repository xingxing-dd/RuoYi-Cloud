package com.ruoyi.market.domain.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dev
 * @ClassName KLineQueryReq
 * @Date 2023/8/29 12:17
 * @Version v1.0
 **/
@Data
@ToString
public class KLineQueryReq implements Serializable {

    /**
     * 产品code
     */
    private String productCode;

    /**
     * 类型
     */
    private String type;

}
