package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.RechargeNetwork;

/**
 * 充币地址Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
public interface RechargeNetworkMapper 
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
     * 删除充币地址
     * 
     * @param id 充币地址主键
     * @return 结果
     */
    public int deleteRechargeNetworkById(Long id);

    /**
     * 批量删除充币地址
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRechargeNetworkByIds(Long[] ids);
}
