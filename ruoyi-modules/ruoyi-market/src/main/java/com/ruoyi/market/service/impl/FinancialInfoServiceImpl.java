package com.ruoyi.market.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.FinancialInfoMapper;
import com.ruoyi.market.domain.FinancialInfo;
import com.ruoyi.market.service.IFinancialInfoService;

/**
 * 理财产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-19
 */
@Service
public class FinancialInfoServiceImpl implements IFinancialInfoService 
{
    @Autowired
    private FinancialInfoMapper financialInfoMapper;

    /**
     * 查询理财产品
     * 
     * @param id 理财产品主键
     * @return 理财产品
     */
    @Override
    public FinancialInfo selectFinancialInfoById(Long id)
    {
        return financialInfoMapper.selectFinancialInfoById(id);
    }

    /**
     * 查询理财产品列表
     * 
     * @param financialInfo 理财产品
     * @return 理财产品
     */
    @Override
    public List<FinancialInfo> selectFinancialInfoList(FinancialInfo financialInfo)
    {
        return financialInfoMapper.selectFinancialInfoList(financialInfo);
    }

    /**
     * 新增理财产品
     * 
     * @param financialInfo 理财产品
     * @return 结果
     */
    @Override
    public int insertFinancialInfo(FinancialInfo financialInfo)
    {
        return financialInfoMapper.insertFinancialInfo(financialInfo);
    }

    /**
     * 修改理财产品
     * 
     * @param financialInfo 理财产品
     * @return 结果
     */
    @Override
    public int updateFinancialInfo(FinancialInfo financialInfo)
    {
        return financialInfoMapper.updateFinancialInfo(financialInfo);
    }

    /**
     * 批量删除理财产品
     * 
     * @param ids 需要删除的理财产品主键
     * @return 结果
     */
    @Override
    public int deleteFinancialInfoByIds(Long[] ids)
    {
        return financialInfoMapper.deleteFinancialInfoByIds(ids);
    }

    /**
     * 删除理财产品信息
     * 
     * @param id 理财产品主键
     * @return 结果
     */
    @Override
    public int deleteFinancialInfoById(Long id)
    {
        return financialInfoMapper.deleteFinancialInfoById(id);
    }
}
