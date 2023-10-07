package com.ruoyi.market.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ProductConfigVo implements Serializable {

    /** 每张数量 */
    private BigDecimal eachSheetNum;

    /** 最小倍率 */
    private Double minMultiplier;

    /** 最大倍率 */
    private Double maxMultiplier;

    /** 倍率步长 */
    private Double multiplierStepSize;

    /** 最小张数 */
    private Double minSheetNum;

    /** 最大张数 */
    private Double maxSheetNum;

    /** 张数步长 */
    private Double sheetStepSize;

    /** 价格步长 */
    private Double priceStepSize;

}
