package com.ruoyi.client.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientUserAuthMapper;
import com.ruoyi.client.domain.ClientUserAuth;
import com.ruoyi.client.service.IClientUserAuthService;
import org.springframework.util.CollectionUtils;

/**
 * 实名认证Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
@Service
public class ClientUserAuthServiceImpl implements IClientUserAuthService
{
    @Autowired
    private ClientUserAuthMapper clientUserAuthMapper;

    /**
     * 查询实名认证
     * 
     * @param id 实名认证主键
     * @return 实名认证
     */
    @Override
    public ClientUserAuth selectClientUserAuthById(Long id)
    {
        return clientUserAuthMapper.selectClientUserAuthById(id);
    }

    /**
     * 查询实名认证列表
     * 
     * @param clientUserAuth 实名认证
     * @return 实名认证
     */
    @Override
    public List<ClientUserAuth> selectClientUserAuthList(ClientUserAuth clientUserAuth)
    {
        return clientUserAuthMapper.selectClientUserAuthList(clientUserAuth);
    }

    /**
     * 新增实名认证
     * 
     * @param clientUserAuth 实名认证
     * @return 结果
     */
    @Override
    public int insertClientUserAuth(ClientUserAuth clientUserAuth)
    {
        clientUserAuth.setCreateTime(DateUtils.getNowDate());
        return clientUserAuthMapper.insertClientUserAuth(clientUserAuth);
    }

    /**
     * 修改实名认证
     * 
     * @param clientUserAuth 实名认证
     * @return 结果
     */
    @Override
    public int updateClientUserAuth(ClientUserAuth clientUserAuth)
    {
        clientUserAuth.setUpdateTime(DateUtils.getNowDate());
        return clientUserAuthMapper.updateClientUserAuth(clientUserAuth);
    }

    /**
     * 批量删除实名认证
     * 
     * @param ids 需要删除的实名认证主键
     * @return 结果
     */
    @Override
    public int deleteClientUserAuthByIds(Long[] ids)
    {
        return clientUserAuthMapper.deleteClientUserAuthByIds(ids);
    }

    /**
     * 删除实名认证信息
     * 
     * @param id 实名认证主键
     * @return 结果
     */
    @Override
    public int deleteClientUserAuthById(Long id)
    {
        return clientUserAuthMapper.deleteClientUserAuthById(id);
    }

    @Override
    public ClientUserAuth detail() {
        ClientUserAuth clientUserAuth = new ClientUserAuth();
        clientUserAuth.setUserId(SecurityContextHolder.getUserId());
        List<ClientUserAuth> clientUserAuths = clientUserAuthMapper.selectClientUserAuthList(clientUserAuth);
        if (CollectionUtils.isEmpty(clientUserAuths)) {
            return null;
        }
        return clientUserAuths.get(0);
    }

    @Override
    public boolean submitAuth(ClientUserAuth clientUserAuth) {
        if (clientUserAuth.getId() != null) {
            clientUserAuth.setStatus(ClientConstant.VALID);
            clientUserAuth.setCreateTime(new Date());
            clientUserAuth.setUpdateBy(SecurityContextHolder.getUserName());
            clientUserAuthMapper.updateClientUserAuth(clientUserAuth);
        } else {
            clientUserAuth.setUserId(SecurityContextHolder.getUserId());
            clientUserAuth.setUserName(SecurityContextHolder.getUserName());
            clientUserAuth.setStatus(ClientConstant.VALID);
            clientUserAuth.setCreateTime(new Date());
            clientUserAuth.setCreateBy(SecurityContextHolder.getUserName());
            clientUserAuth.setUpdateTime(new Date());
            clientUserAuth.setUpdateBy(SecurityContextHolder.getUserName());
            clientUserAuthMapper.insertClientUserAuth(clientUserAuth);
        }
        return true;
    }
}
