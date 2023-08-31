package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.FundAccountInfo;

/**
 * 账号管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-31
 */
public interface FundAccountInfoMapper 
{
    /**
     * 查询账号管理
     * 
     * @param id 账号管理主键
     * @return 账号管理
     */
    public FundAccountInfo selectFundAccountInfoById(Long id);

    /**
     * 查询账号管理列表
     * 
     * @param fundAccountInfo 账号管理
     * @return 账号管理集合
     */
    public List<FundAccountInfo> selectFundAccountInfoList(FundAccountInfo fundAccountInfo);

    /**
     * 新增账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    public int insertFundAccountInfo(FundAccountInfo fundAccountInfo);

    /**
     * 修改账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    public int updateFundAccountInfo(FundAccountInfo fundAccountInfo);

    /**
     * 删除账号管理
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    public int deleteFundAccountInfoById(Long id);

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFundAccountInfoByIds(Long[] ids);
}
