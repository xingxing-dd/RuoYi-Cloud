package com.ruoyi.market.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.ProductConfigMapper;
import com.ruoyi.market.domain.ProductConfig;
import com.ruoyi.market.service.IProductConfigService;

/**
 * 产品配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-28
 */
@Service
public class ProductConfigServiceImpl implements IProductConfigService 
{
    @Autowired
    private ProductConfigMapper productConfigMapper;

    /**
     * 查询产品配置
     * 
     * @param id 产品配置主键
     * @return 产品配置
     */
    @Override
    public ProductConfig selectProductConfigById(Long id)
    {
        return productConfigMapper.selectProductConfigById(id);
    }

    /**
     * 查询产品配置列表
     * 
     * @param productConfig 产品配置
     * @return 产品配置
     */
    @Override
    public List<ProductConfig> selectProductConfigList(ProductConfig productConfig)
    {
        return productConfigMapper.selectProductConfigList(productConfig);
    }

    /**
     * 新增产品配置
     * 
     * @param productConfig 产品配置
     * @return 结果
     */
    @Override
    public int insertProductConfig(ProductConfig productConfig)
    {
        productConfig.setCreateTime(DateUtils.getNowDate());
        return productConfigMapper.insertProductConfig(productConfig);
    }

    /**
     * 修改产品配置
     * 
     * @param productConfig 产品配置
     * @return 结果
     */
    @Override
    public int updateProductConfig(ProductConfig productConfig)
    {
        productConfig.setUpdateTime(DateUtils.getNowDate());
        return productConfigMapper.updateProductConfig(productConfig);
    }

    /**
     * 批量删除产品配置
     * 
     * @param ids 需要删除的产品配置主键
     * @return 结果
     */
    @Override
    public int deleteProductConfigByIds(Long[] ids)
    {
        return productConfigMapper.deleteProductConfigByIds(ids);
    }

    /**
     * 删除产品配置信息
     * 
     * @param id 产品配置主键
     * @return 结果
     */
    @Override
    public int deleteProductConfigById(Long id)
    {
        return productConfigMapper.deleteProductConfigById(id);
    }
}
