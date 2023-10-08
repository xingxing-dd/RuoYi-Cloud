package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.ClientUserAuth;

/**
 * 实名认证Service接口
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
public interface IClientUserAuthService 
{
    /**
     * 查询实名认证
     * 
     * @param id 实名认证主键
     * @return 实名认证
     */
    public ClientUserAuth selectClientUserAuthById(Long id);

    /**
     * 查询实名认证列表
     * 
     * @param clientUserAuth 实名认证
     * @return 实名认证集合
     */
    public List<ClientUserAuth> selectClientUserAuthList(ClientUserAuth clientUserAuth);

    /**
     * 新增实名认证
     * 
     * @param clientUserAuth 实名认证
     * @return 结果
     */
    public int insertClientUserAuth(ClientUserAuth clientUserAuth);

    /**
     * 修改实名认证
     * 
     * @param clientUserAuth 实名认证
     * @return 结果
     */
    public int updateClientUserAuth(ClientUserAuth clientUserAuth);

    /**
     * 批量删除实名认证
     * 
     * @param ids 需要删除的实名认证主键集合
     * @return 结果
     */
    public int deleteClientUserAuthByIds(Long[] ids);

    /**
     * 删除实名认证信息
     * 
     * @param id 实名认证主键
     * @return 结果
     */
    public int deleteClientUserAuthById(Long id);

    /**
     * 查询当前账户详情
     * @return
     */
    ClientUserAuth detail();

    /**
     * 提交实名认证信息
     * @param clientUserAuth
     * @return
     */
    public boolean submitAuth(ClientUserAuth clientUserAuth);
}
