package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.ProductConfig;

/**
 * 产品配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public interface ProductConfigMapper 
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
     * 删除产品配置
     * 
     * @param id 产品配置主键
     * @return 结果
     */
    public int deleteProductConfigById(Long id);

    /**
     * 批量删除产品配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductConfigByIds(Long[] ids);
}
