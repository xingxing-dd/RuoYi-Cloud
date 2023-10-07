package com.ruoyi.client.service;

import java.util.List;

import com.ruoyi.client.domain.TradeFeeConfig;

/**
 * 费用配置Service接口
 * 
 * @author ruoyi
 * @date 2023-10-05
 */
public interface ITradeFeeConfigService 
{
    /**
     * 查询费用配置
     * 
     * @param id 费用配置主键
     * @return 费用配置
     */
    public TradeFeeConfig selectTradeFeeConfigById(Long id);

    /**
     * 查询费用配置列表
     * 
     * @param tradeFeeConfig 费用配置
     * @return 费用配置集合
     */
    public List<TradeFeeConfig> selectTradeFeeConfigList(TradeFeeConfig tradeFeeConfig);

    /**
     * 新增费用配置
     * 
     * @param tradeFeeConfig 费用配置
     * @return 结果
     */
    public int insertTradeFeeConfig(TradeFeeConfig tradeFeeConfig);

    /**
     * 修改费用配置
     * 
     * @param tradeFeeConfig 费用配置
     * @return 结果
     */
    public int updateTradeFeeConfig(TradeFeeConfig tradeFeeConfig);

    /**
     * 批量删除费用配置
     * 
     * @param ids 需要删除的费用配置主键集合
     * @return 结果
     */
    public int deleteTradeFeeConfigByIds(Long[] ids);

    /**
     * 删除费用配置信息
     * 
     * @param id 费用配置主键
     * @return 结果
     */
    public int deleteTradeFeeConfigById(Long id);

    /**
     * 查询交易费用
     * @param userId
     * @param tradeType
     * @param configType
     * @return
     */
    public TradeFeeConfig selectTradeConfig(Long userId, String tradeType, String configType);
}
