package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.RechargeNetwork;

/**
 * 充币地址Service接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface IRechargeNetworkService 
{
    /**
     * 查询充币地址
     * 
     * @param id 充币地址主键
     * @return 充币地址
     */
    public RechargeNetwork selectRechargeNetworkById(Long id);

    /**
     * 查询充币地址列表
     * 
     * @param rechargeNetwork 充币地址
     * @return 充币地址集合
     */
    public List<RechargeNetwork> selectRechargeNetworkList(RechargeNetwork rechargeNetwork);

    /**
     * 新增充币地址
     * 
     * @param rechargeNetwork 充币地址
     * @return 结果
     */
    public int insertRechargeNetwork(RechargeNetwork rechargeNetwork);

    /**
     * 修改充币地址
     * 
     * @param rechargeNetwork 充币地址
     * @return 结果
     */
    public int updateRechargeNetwork(RechargeNetwork rechargeNetwork);

    /**
     * 批量删除充币地址
     * 
     * @param ids 需要删除的充币地址主键集合
     * @return 结果
     */
    public int deleteRechargeNetworkByIds(Long[] ids);

    /**
     * 删除充币地址信息
     * 
     * @param id 充币地址主键
     * @return 结果
     */
    public int deleteRechargeNetworkById(Long id);
}
