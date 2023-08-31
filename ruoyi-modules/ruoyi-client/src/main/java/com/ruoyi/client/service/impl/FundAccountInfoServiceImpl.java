package com.ruoyi.client.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.FundAccountInfoMapper;
import com.ruoyi.client.domain.FundAccountInfo;
import com.ruoyi.client.service.IFundAccountInfoService;

/**
 * 账号管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-31
 */
@Service
public class FundAccountInfoServiceImpl implements IFundAccountInfoService 
{
    @Autowired
    private FundAccountInfoMapper fundAccountInfoMapper;

    /**
     * 查询账号管理
     * 
     * @param id 账号管理主键
     * @return 账号管理
     */
    @Override
    public FundAccountInfo selectFundAccountInfoById(Long id)
    {
        return fundAccountInfoMapper.selectFundAccountInfoById(id);
    }

    /**
     * 查询账号管理列表
     * 
     * @param fundAccountInfo 账号管理
     * @return 账号管理
     */
    @Override
    public List<FundAccountInfo> selectFundAccountInfoList(FundAccountInfo fundAccountInfo)
    {
        return fundAccountInfoMapper.selectFundAccountInfoList(fundAccountInfo);
    }

    /**
     * 新增账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    @Override
    public int insertFundAccountInfo(FundAccountInfo fundAccountInfo)
    {
        fundAccountInfo.setCreateTime(DateUtils.getNowDate());
        return fundAccountInfoMapper.insertFundAccountInfo(fundAccountInfo);
    }

    /**
     * 修改账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    @Override
    public int updateFundAccountInfo(FundAccountInfo fundAccountInfo)
    {
        fundAccountInfo.setUpdateTime(DateUtils.getNowDate());
        return fundAccountInfoMapper.updateFundAccountInfo(fundAccountInfo);
    }

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的账号管理主键
     * @return 结果
     */
    @Override
    public int deleteFundAccountInfoByIds(Long[] ids)
    {
        return fundAccountInfoMapper.deleteFundAccountInfoByIds(ids);
    }

    /**
     * 删除账号管理信息
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    @Override
    public int deleteFundAccountInfoById(Long id)
    {
        return fundAccountInfoMapper.deleteFundAccountInfoById(id);
    }
}
