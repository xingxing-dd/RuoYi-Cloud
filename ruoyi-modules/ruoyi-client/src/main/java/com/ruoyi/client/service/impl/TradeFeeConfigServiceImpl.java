package com.ruoyi.client.service.impl;

import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.TradeFeeConfigMapper;
import com.ruoyi.client.domain.TradeFeeConfig;
import com.ruoyi.client.service.ITradeFeeConfigService;

/**
 * 费用配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-05
 */
@Service
public class TradeFeeConfigServiceImpl implements ITradeFeeConfigService 
{
    @Autowired
    private TradeFeeConfigMapper tradeFeeConfigMapper;

    /**
     * 查询费用配置
     * 
     * @param id 费用配置主键
     * @return 费用配置
     */
    @Override
    public TradeFeeConfig selectTradeFeeConfigById(Long id)
    {
        return tradeFeeConfigMapper.selectTradeFeeConfigById(id);
    }

    /**
     * 查询费用配置列表
     * 
     * @param tradeFeeConfig 费用配置
     * @return 费用配置
     */
    @Override
    public List<TradeFeeConfig> selectTradeFeeConfigList(TradeFeeConfig tradeFeeConfig)
    {
        return tradeFeeConfigMapper.selectTradeFeeConfigList(tradeFeeConfig);
    }

    /**
     * 新增费用配置
     * 
     * @param tradeFeeConfig 费用配置
     * @return 结果
     */
    @Override
    public int insertTradeFeeConfig(TradeFeeConfig tradeFeeConfig)
    {
        tradeFeeConfig.setCreateTime(DateUtils.getNowDate());
        return tradeFeeConfigMapper.insertTradeFeeConfig(tradeFeeConfig);
    }

    /**
     * 修改费用配置
     * 
     * @param tradeFeeConfig 费用配置
     * @return 结果
     */
    @Override
    public int updateTradeFeeConfig(TradeFeeConfig tradeFeeConfig)
    {
        tradeFeeConfig.setUpdateTime(DateUtils.getNowDate());
        return tradeFeeConfigMapper.updateTradeFeeConfig(tradeFeeConfig);
    }

    /**
     * 批量删除费用配置
     * 
     * @param ids 需要删除的费用配置主键
     * @return 结果
     */
    @Override
    public int deleteTradeFeeConfigByIds(Long[] ids)
    {
        return tradeFeeConfigMapper.deleteTradeFeeConfigByIds(ids);
    }

    /**
     * 删除费用配置信息
     * 
     * @param id 费用配置主键
     * @return 结果
     */
    @Override
    public int deleteTradeFeeConfigById(Long id)
    {
        return tradeFeeConfigMapper.deleteTradeFeeConfigById(id);
    }

    @Override
    public TradeFeeConfig selectTradeConfig(Long userId, String tradeType, String configType) {
        TradeFeeConfig selectParams = new TradeFeeConfig();
        selectParams.setUserId(userId);
        selectParams.setTradeType(tradeType);
        selectParams.setConfigType(configType);
        List<TradeFeeConfig> tradeConfigs = tradeFeeConfigMapper.selectTradeFeeConfigList(selectParams);
        if (!CollectionUtils.isEmpty(tradeConfigs)) {
            return tradeConfigs.get(0);
        }
        selectParams.setUserId(-1L);
        tradeConfigs = tradeFeeConfigMapper.selectTradeFeeConfigList(selectParams);
        if (!CollectionUtils.isEmpty(tradeConfigs)) {
            return tradeConfigs.get(0);
        }
        return null;
    }
}
