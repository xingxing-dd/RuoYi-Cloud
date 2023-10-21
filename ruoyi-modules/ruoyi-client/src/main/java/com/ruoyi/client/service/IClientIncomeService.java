package com.ruoyi.client.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.client.controller.req.ClientIncomeReq;
import com.ruoyi.client.controller.resp.ClientIncomeResp;
import com.ruoyi.client.domain.ClientIncome;

/**
 * 用户收益Service接口
 * 
 * @author ruoyi
 * @date 2023-10-21
 */
public interface IClientIncomeService 
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
     * 批量删除用户收益
     * 
     * @param ids 需要删除的用户收益主键集合
     * @return 结果
     */
    public int deleteClientIncomeByIds(Long[] ids);

    /**
     * 删除用户收益信息
     * 
     * @param id 用户收益主键
     * @return 结果
     */
    public int deleteClientIncomeById(Long id);

    /**
     * 创建用户收益
     * @param incomeType
     * @param income
     * @param remark
     */
    public void createUserIncome(Long userId, String userName, Long incomeType, BigDecimal income, String incomeDate, String remark);

    /**
     * 查询用户收益
     * @param req
     * @return
     */
    public ClientIncomeResp queryUserIncome(ClientIncomeReq req);

}
