package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.FinancialInfo;

/**
 * 理财产品Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-19
 */
public interface FinancialInfoMapper 
{
    /**
     * 查询理财产品
     * 
     * @param id 理财产品主键
     * @return 理财产品
     */
    public FinancialInfo selectFinancialInfoById(Long id);

    /**
     * 查询理财产品列表
     * 
     * @param financialInfo 理财产品
     * @return 理财产品集合
     */
    public List<FinancialInfo> selectFinancialInfoList(FinancialInfo financialInfo);

    /**
     * 新增理财产品
     * 
     * @param financialInfo 理财产品
     * @return 结果
     */
    public int insertFinancialInfo(FinancialInfo financialInfo);

    /**
     * 修改理财产品
     * 
     * @param financialInfo 理财产品
     * @return 结果
     */
    public int updateFinancialInfo(FinancialInfo financialInfo);

    /**
     * 删除理财产品
     * 
     * @param id 理财产品主键
     * @return 结果
     */
    public int deleteFinancialInfoById(Long id);

    /**
     * 批量删除理财产品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialInfoByIds(Long[] ids);
}
