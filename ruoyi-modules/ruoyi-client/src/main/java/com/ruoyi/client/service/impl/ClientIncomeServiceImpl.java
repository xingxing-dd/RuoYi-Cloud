package com.ruoyi.client.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.client.controller.req.ClientIncomeReq;
import com.ruoyi.client.controller.resp.ClientIncomeResp;
import com.ruoyi.common.core.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientIncomeMapper;
import com.ruoyi.client.domain.ClientIncome;
import com.ruoyi.client.service.IClientIncomeService;

/**
 * 用户收益Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-21
 */
@Slf4j
@Service
public class ClientIncomeServiceImpl implements IClientIncomeService 
{
    @Autowired
    private ClientIncomeMapper clientIncomeMapper;

    /**
     * 查询用户收益
     * 
     * @param id 用户收益主键
     * @return 用户收益
     */
    @Override
    public ClientIncome selectClientIncomeById(Long id)
    {
        return clientIncomeMapper.selectClientIncomeById(id);
    }

    /**
     * 查询用户收益列表
     * 
     * @param clientIncome 用户收益
     * @return 用户收益
     */
    @Override
    public List<ClientIncome> selectClientIncomeList(ClientIncome clientIncome)
    {
        return clientIncomeMapper.selectClientIncomeList(clientIncome);
    }

    /**
     * 新增用户收益
     * 
     * @param clientIncome 用户收益
     * @return 结果
     */
    @Override
    public int insertClientIncome(ClientIncome clientIncome)
    {
        return clientIncomeMapper.insertClientIncome(clientIncome);
    }

    /**
     * 修改用户收益
     * 
     * @param clientIncome 用户收益
     * @return 结果
     */
    @Override
    public int updateClientIncome(ClientIncome clientIncome)
    {
        return clientIncomeMapper.updateClientIncome(clientIncome);
    }

    /**
     * 批量删除用户收益
     * 
     * @param ids 需要删除的用户收益主键
     * @return 结果
     */
    @Override
    public int deleteClientIncomeByIds(Long[] ids)
    {
        return clientIncomeMapper.deleteClientIncomeByIds(ids);
    }

    /**
     * 删除用户收益信息
     * 
     * @param id 用户收益主键
     * @return 结果
     */
    @Override
    public int deleteClientIncomeById(Long id)
    {
        return clientIncomeMapper.deleteClientIncomeById(id);
    }

    @Override
    public void createUserIncome(Long userId, String userName, Long incomeType, BigDecimal income, String incomeDate, String remark) {
        ClientIncome clientIncome = new ClientIncome();
        clientIncome.setUserId(userId);
        clientIncome.setUserName(userName);
        clientIncome.setIncomeType(incomeType);
        clientIncome.setIncomeDate(incomeDate);
        clientIncome.setRemark(remark);
        clientIncome.setCreateAt(new Date());
        clientIncome.setCreateBy(userName);
        clientIncome.setCreateAt(new Date());
        clientIncome.setUpdateAt(new Date());
        clientIncome.setUpdateBy(userName);
        log.info("生成收益:{}", clientIncome);
        clientIncomeMapper.insertClientIncome(clientIncome);
    }

    @Override
    public ClientIncomeResp queryUserIncome(ClientIncomeReq req) {
        ClientIncome clientIncome = new ClientIncome();
        clientIncome.setUserId(SecurityContextHolder.getUserId());
        clientIncome.setIncomeType(req.getIncomeType());
        clientIncome.setIncomeDate(req.getIncomeDate());
        clientIncome.setStatus(0L);
        List<ClientIncome> clientIncomes = clientIncomeMapper.selectClientIncomeList(clientIncome);
        ClientIncomeResp clientIncomeResp = new ClientIncomeResp();
        if (CollectionUtils.isEmpty(clientIncomes)) {
            clientIncomeResp.setIncome(BigDecimal.ZERO);
        } else {
            clientIncomeResp.setIncome(clientIncomes.stream().map(ClientIncome::getIncome).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        }
        clientIncomeResp.setIncomeDate(req.getIncomeDate());
        return clientIncomeResp;
    }
}
