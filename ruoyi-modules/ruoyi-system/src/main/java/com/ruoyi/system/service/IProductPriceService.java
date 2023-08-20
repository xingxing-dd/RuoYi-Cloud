package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ProductPrice;

/**
 * 大盘价格数据源Service接口
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public interface IProductPriceService 
{
    /**
     * 查询大盘价格数据源
     * 
     * @param id 大盘价格数据源主键
     * @return 大盘价格数据源
     */
    public ProductPrice selectProductPriceById(Long id);

    /**
     * 查询大盘价格数据源列表
     * 
     * @param productPrice 大盘价格数据源
     * @return 大盘价格数据源集合
     */
    public List<ProductPrice> selectProductPriceList(ProductPrice productPrice);

    /**
     * 新增大盘价格数据源
     * 
     * @param productPrice 大盘价格数据源
     * @return 结果
     */
    public int insertProductPrice(ProductPrice productPrice);

    /**
     * 修改大盘价格数据源
     * 
     * @param productPrice 大盘价格数据源
     * @return 结果
     */
    public int updateProductPrice(ProductPrice productPrice);

    /**
     * 批量删除大盘价格数据源
     * 
     * @param ids 需要删除的大盘价格数据源主键集合
     * @return 结果
     */
    public int deleteProductPriceByIds(Long[] ids);

    /**
     * 删除大盘价格数据源信息
     * 
     * @param id 大盘价格数据源主键
     * @return 结果
     */
    public int deleteProductPriceById(Long id);
}
