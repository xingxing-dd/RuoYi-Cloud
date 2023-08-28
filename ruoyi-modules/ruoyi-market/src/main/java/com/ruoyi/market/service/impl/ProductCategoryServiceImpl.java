package com.ruoyi.market.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.ProductCategoryMapper;
import com.ruoyi.market.domain.ProductCategory;
import com.ruoyi.market.service.IProductCategoryService;

/**
 * 产品类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-28
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService 
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询产品类别
     * 
     * @param id 产品类别主键
     * @return 产品类别
     */
    @Override
    public ProductCategory selectProductCategoryById(Long id)
    {
        return productCategoryMapper.selectProductCategoryById(id);
    }

    /**
     * 查询产品类别列表
     * 
     * @param productCategory 产品类别
     * @return 产品类别
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增产品类别
     * 
     * @param productCategory 产品类别
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory)
    {
        productCategory.setCreateTime(DateUtils.getNowDate());
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改产品类别
     * 
     * @param productCategory 产品类别
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory)
    {
        productCategory.setUpdateTime(DateUtils.getNowDate());
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除产品类别
     * 
     * @param ids 需要删除的产品类别主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Long[] ids)
    {
        return productCategoryMapper.deleteProductCategoryByIds(ids);
    }

    /**
     * 删除产品类别信息
     * 
     * @param id 产品类别主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Long id)
    {
        return productCategoryMapper.deleteProductCategoryById(id);
    }
}
