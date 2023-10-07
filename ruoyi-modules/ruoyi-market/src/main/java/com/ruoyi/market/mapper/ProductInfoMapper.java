package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.ProductInfo;

/**
 * 产品信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-28
 */
public interface ProductInfoMapper 
{
    /**
     * 查询产品信息
     * 
     * @param id 产品信息主键
     * @return 产品信息
     */
    public ProductInfo selectProductInfoById(Long id);

    /**
     * 查询产品信息列表
     * 
     * @param productInfo 产品信息
     * @return 产品信息集合
     */
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo);

    /**
     * 查询产品信息列表
     * @param productInfo
     * @return
     */
    public List<ProductInfo> selectProductInfoListOrderByPriority(ProductInfo productInfo);

    /**
     * 新增产品信息
     * 
     * @param productInfo 产品信息
     * @return 结果
     */
    public int insertProductInfo(ProductInfo productInfo);

    /**
     * 修改产品信息
     * 
     * @param productInfo 产品信息
     * @return 结果
     */
    public int updateProductInfo(ProductInfo productInfo);

    /**
     * 删除产品信息
     * 
     * @param id 产品信息主键
     * @return 结果
     */
    public int deleteProductInfoById(Long id);

    /**
     * 批量删除产品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductInfoByIds(Long[] ids);
}
