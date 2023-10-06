package com.ruoyi.market.domain.vo;

import com.ruoyi.common.core.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ProductConfigVo implements Serializable {

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
