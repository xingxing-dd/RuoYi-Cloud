package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.ClientIncome;

/**
 * 用户收益Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-21
 */
public interface ClientIncomeMapper 
{
    /**
     * 查询用户收益
     * 
     * @param id 用户收益主键
     * @return 用户收益
     */
    public ClientIncome selectClientIncomeById(Long id);

    /**
     * 查询用户收益列表
     * 
     * @param clientIncome 用户收益
     * @return 用户收益集合
     */
    public List<ClientIncome> selectClientIncomeList(ClientIncome clientIncome);

    /**
     * 新增用户收益
     * 
     * @param clientIncome 用户收益
     * @return 结果
     */
    public int insertClientIncome(ClientIncome clientIncome);

    /**
     * 修改用户收益
     * 
     * @param clientIncome 用户收益
     * @return 结果
     */
    public int updateClientIncome(ClientIncome clientIncome);

    /**
     * 删除用户收益
     * 
     * @param id 用户收益主键
     * @return 结果
     */
    public int deleteClientIncomeById(Long id);

    /**
     * 批量删除用户收益
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientIncomeByIds(Long[] ids);
}
