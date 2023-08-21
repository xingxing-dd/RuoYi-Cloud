package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.ProductCategory;

/**
 * 产品类别Service接口
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public interface IProductCategoryService 
{
    /**
     * 查询产品类别
     * 
     * @param id 产品类别主键
     * @return 产品类别
     */
    public ProductCategory selectProductCategoryById(Long id);

    /**
     * 查询产品类别列表
     * 
     * @param productCategory 产品类别
     * @return 产品类别集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增产品类别
     * 
     * @param productCategory 产品类别
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改产品类别
     * 
     * @param productCategory 产品类别
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 批量删除产品类别
     * 
     * @param ids 需要删除的产品类别主键集合
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] ids);

    /**
     * 删除产品类别信息
     * 
     * @param id 产品类别主键
     * @return 结果
     */
    public int deleteProductCategoryById(Long id);
}
