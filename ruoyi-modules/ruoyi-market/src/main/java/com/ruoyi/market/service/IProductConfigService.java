package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.ProductConfig;

/**
 * 产品配置Service接口
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
public interface IProductConfigService 
{
    /**
     * 查询产品配置
     * 
     * @param id 产品配置主键
     * @return 产品配置
     */
    public ProductConfig selectProductConfigById(Long id);

    /**
     * 查询产品配置列表
     * 
     * @param productConfig 产品配置
     * @return 产品配置集合
     */
    public List<ProductConfig> selectProductConfigList(ProductConfig productConfig);

    /**
     * 新增产品配置
     * 
     * @param productConfig 产品配置
     * @return 结果
     */
    public int insertProductConfig(ProductConfig productConfig);

    /**
     * 修改产品配置
     * 
     * @param productConfig 产品配置
     * @return 结果
     */
    public int updateProductConfig(ProductConfig productConfig);

    /**
     * 批量删除产品配置
     * 
     * @param ids 需要删除的产品配置主键集合
     * @return 结果
     */
    public int deleteProductConfigByIds(Long[] ids);

    /**
     * 删除产品配置信息
     * 
     * @param id 产品配置主键
     * @return 结果
     */
    public int deleteProductConfigById(Long id);
}
