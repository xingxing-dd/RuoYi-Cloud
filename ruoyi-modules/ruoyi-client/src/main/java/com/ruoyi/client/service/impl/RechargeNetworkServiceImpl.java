package com.ruoyi.client.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.RechargeNetworkMapper;
import com.ruoyi.client.domain.RechargeNetwork;
import com.ruoyi.client.service.IRechargeNetworkService;

/**
 * 充币地址Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@Service
public class RechargeNetworkServiceImpl implements IRechargeNetworkService 
{
    @Autowired
    private RechargeNetworkMapper rechargeNetworkMapper;

    /**
     * 查询充币地址
     * 
     * @param id 充币地址主键
     * @return 充币地址
     */
    @Override
    public RechargeNetwork selectRechargeNetworkById(Long id)
    {
        return rechargeNetworkMapper.selectRechargeNetworkById(id);
    }

    /**
     * 查询充币地址列表
     * 
     * @param rechargeNetwork 充币地址
     * @return 充币地址
     */
    @Override
    public List<RechargeNetwork> selectRechargeNetworkList(RechargeNetwork rechargeNetwork)
    {
        return rechargeNetworkMapper.selectRechargeNetworkList(rechargeNetwork);
    }

    /**
     * 新增充币地址
     * 
     * @param rechargeNetwork 充币地址
     * @return 结果
     */
    @Override
    public int insertRechargeNetwork(RechargeNetwork rechargeNetwork)
    {
        rechargeNetwork.setCreateTime(DateUtils.getNowDate());
        return rechargeNetworkMapper.insertRechargeNetwork(rechargeNetwork);
    }

    /**
     * 修改充币地址
     * 
     * @param rechargeNetwork 充币地址
     * @return 结果
     */
    @Override
    public int updateRechargeNetwork(RechargeNetwork rechargeNetwork)
    {
        rechargeNetwork.setUpdateTime(DateUtils.getNowDate());
        return rechargeNetworkMapper.updateRechargeNetwork(rechargeNetwork);
    }

    /**
     * 批量删除充币地址
     * 
     * @param ids 需要删除的充币地址主键
     * @return 结果
     */
    @Override
    public int deleteRechargeNetworkByIds(Long[] ids)
    {
        return rechargeNetworkMapper.deleteRechargeNetworkByIds(ids);
    }

    /**
     * 删除充币地址信息
     * 
     * @param id 充币地址主键
     * @return 结果
     */
    @Override
    public int deleteRechargeNetworkById(Long id)
    {
        return rechargeNetworkMapper.deleteRechargeNetworkById(id);
    }
}
