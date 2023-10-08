package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.EntrustOrder;

/**
 * 委托订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
public interface EntrustOrderMapper 
{
    /**
     * 查询委托订单
     * 
     * @param id 委托订单主键
     * @return 委托订单
     */
    public EntrustOrder selectEntrustOrderById(Long id);

    /**
     * 查询委托订单列表
     * 
     * @param entrustOrder 委托订单
     * @return 委托订单集合
     */
    public List<EntrustOrder> selectEntrustOrderList(EntrustOrder entrustOrder);

    /**
     * 新增委托订单
     * 
     * @param entrustOrder 委托订单
     * @return 结果
     */
    public int insertEntrustOrder(EntrustOrder entrustOrder);

    /**
     * 修改委托订单
     * 
     * @param entrustOrder 委托订单
     * @return 结果
     */
    public int updateEntrustOrder(EntrustOrder entrustOrder);

    /**
     * 删除委托订单
     * 
     * @param id 委托订单主键
     * @return 结果
     */
    public int deleteEntrustOrderById(Long id);

    /**
     * 批量删除委托订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEntrustOrderByIds(Long[] ids);
}
