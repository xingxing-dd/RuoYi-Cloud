package com.ruoyi.market.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.ProductInfoMapper;
import com.ruoyi.market.domain.ProductInfo;
import com.ruoyi.market.service.IProductInfoService;

/**
 * 产品信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-28
 */
@Service
public class ProductInfoServiceImpl implements IProductInfoService 
{
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询产品信息
     * 
     * @param id 产品信息主键
     * @return 产品信息
     */
    @Override
    public ProductInfo selectProductInfoById(Long id)
    {
        return productInfoMapper.selectProductInfoById(id);
    }

    /**
     * 查询产品信息列表
     * 
     * @param productInfo 产品信息
     * @return 产品信息
     */
    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo)
    {
        return productInfoMapper.selectProductInfoList(productInfo);
    }

    @Override
    @Cacheable(value = "category")
    public List<ProductInfo> selectProductInfoByCategory(String category) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryCode(category);
        return productInfoMapper.selectProductInfoList(productInfo);
    }

    /**
     * 新增产品信息
     * 
     * @param productInfo 产品信息
     * @return 结果
     */
    @Override
    public int insertProductInfo(ProductInfo productInfo)
    {
        productInfo.setCreateTime(DateUtils.getNowDate());
        return productInfoMapper.insertProductInfo(productInfo);
    }

    /**
     * 修改产品信息
     * 
     * @param productInfo 产品信息
     * @return 结果
     */
    @Override
    public int updateProductInfo(ProductInfo productInfo)
    {
        productInfo.setUpdateTime(DateUtils.getNowDate());
        return productInfoMapper.updateProductInfo(productInfo);
    }

    /**
     * 批量删除产品信息
     * 
     * @param ids 需要删除的产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductInfoByIds(Long[] ids)
    {
        return productInfoMapper.deleteProductInfoByIds(ids);
    }

    /**
     * 删除产品信息信息
     * 
     * @param id 产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductInfoById(Long id)
    {
        return productInfoMapper.deleteProductInfoById(id);
    }
}
