package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProductPriceMapper;
import com.ruoyi.system.domain.ProductPrice;
import com.ruoyi.system.service.IProductPriceService;

/**
 * 大盘价格数据源Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
@Service
public class ProductPriceServiceImpl implements IProductPriceService 
{
    @Autowired
    private ProductPriceMapper productPriceMapper;

    /**
     * 查询大盘价格数据源
     * 
     * @param id 大盘价格数据源主键
     * @return 大盘价格数据源
     */
    @Override
    public ProductPrice selectProductPriceById(Long id)
    {
        return productPriceMapper.selectProductPriceById(id);
    }

    /**
     * 查询大盘价格数据源列表
     * 
     * @param productPrice 大盘价格数据源
     * @return 大盘价格数据源
     */
    @Override
    public List<ProductPrice> selectProductPriceList(ProductPrice productPrice)
    {
        return productPriceMapper.selectProductPriceList(productPrice);
    }

    /**
     * 新增大盘价格数据源
     * 
     * @param productPrice 大盘价格数据源
     * @return 结果
     */
    @Override
    public int insertProductPrice(ProductPrice productPrice)
    {
        productPrice.setCreateTime(DateUtils.getNowDate());
        return productPriceMapper.insertProductPrice(productPrice);
    }

    /**
     * 修改大盘价格数据源
     * 
     * @param productPrice 大盘价格数据源
     * @return 结果
     */
    @Override
    public int updateProductPrice(ProductPrice productPrice)
    {
        productPrice.setUpdateTime(DateUtils.getNowDate());
        return productPriceMapper.updateProductPrice(productPrice);
    }

    /**
     * 批量删除大盘价格数据源
     * 
     * @param ids 需要删除的大盘价格数据源主键
     * @return 结果
     */
    @Override
    public int deleteProductPriceByIds(Long[] ids)
    {
        return productPriceMapper.deleteProductPriceByIds(ids);
    }

    /**
     * 删除大盘价格数据源信息
     * 
     * @param id 大盘价格数据源主键
     * @return 结果
     */
    @Override
    public int deleteProductPriceById(Long id)
    {
        return productPriceMapper.deleteProductPriceById(id);
    }
}
