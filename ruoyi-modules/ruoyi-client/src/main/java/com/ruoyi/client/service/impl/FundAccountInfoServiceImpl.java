package com.ruoyi.client.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.client.console.FundAccountInfoTypeEnum;
import com.ruoyi.client.controller.req.FundAccountInfoReq;
import com.ruoyi.client.controller.resp.RechargeAccountInfoResp;
import com.ruoyi.client.controller.resp.WithdrawAccountInfoResp;
import com.ruoyi.client.utils.QrCodeUtils;
import com.ruoyi.common.core.constant.ClientConstant;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.FundAccountInfoMapper;
import com.ruoyi.client.domain.FundAccountInfo;
import com.ruoyi.client.service.IFundAccountInfoService;

/**
 * 账号管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-31
 */
@Slf4j
@Service
public class FundAccountInfoServiceImpl implements IFundAccountInfoService 
{
    @Autowired
    private FundAccountInfoMapper fundAccountInfoMapper;

    /**
     * 查询账号管理
     * 
     * @param id 账号管理主键
     * @return 账号管理
     */
    @Override
    public FundAccountInfo selectFundAccountInfoById(Long id)
    {
        return fundAccountInfoMapper.selectFundAccountInfoById(id);
    }

    /**
     * 查询账号管理列表
     * 
     * @param fundAccountInfo 账号管理
     * @return 账号管理
     */
    @Override
    public List<FundAccountInfo> selectFundAccountInfoList(FundAccountInfo fundAccountInfo)
    {
        return fundAccountInfoMapper.selectFundAccountInfoList(fundAccountInfo);
    }

    /**
     * 新增账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    @Override
    public int insertFundAccountInfo(FundAccountInfo fundAccountInfo)
    {
        fundAccountInfo.setCreateTime(DateUtils.getNowDate());
        return fundAccountInfoMapper.insertFundAccountInfo(fundAccountInfo);
    }

    /**
     * 修改账号管理
     * 
     * @param fundAccountInfo 账号管理
     * @return 结果
     */
    @Override
    public int updateFundAccountInfo(FundAccountInfo fundAccountInfo)
    {
        fundAccountInfo.setUpdateTime(DateUtils.getNowDate());
        return fundAccountInfoMapper.updateFundAccountInfo(fundAccountInfo);
    }

    /**
     * 批量删除账号管理
     * 
     * @param ids 需要删除的账号管理主键
     * @return 结果
     */
    @Override
    public int deleteFundAccountInfoByIds(Long[] ids)
    {
        return fundAccountInfoMapper.deleteFundAccountInfoByIds(ids);
    }

    /**
     * 删除账号管理信息
     * 
     * @param id 账号管理主键
     * @return 结果
     */
    @Override
    public int deleteFundAccountInfoById(Long id)
    {
        return fundAccountInfoMapper.deleteFundAccountInfoById(id);
    }

    @Override
    public RechargeAccountInfoResp selectRechargeAcct(FundAccountInfoReq req) {
        FundAccountInfo selectParams = new FundAccountInfo();
        selectParams.setAccountType(req.getAccountType());
        selectParams.setAccountCurrency(req.getAccountCurrency());
        selectParams.setAccountOwnerCode(req.getAccountOwnerCode());
        selectParams.setAccountUsage(FundAccountInfoTypeEnum.RECHARGE.getCode());
        selectParams.setStatus(0L);
        List<FundAccountInfo> fundAccountInfos = fundAccountInfoMapper.selectFundAccountInfoList(selectParams);
        if (CollectionUtils.isEmpty(fundAccountInfos)) {
            return null;
        }
        RechargeAccountInfoResp fundAccountInfo = new RechargeAccountInfoResp();
        BeanUtils.copyProperties(fundAccountInfos.get(0), fundAccountInfo);
        fundAccountInfo.setAccountNoQrCode(QrCodeUtils.createCode(fundAccountInfo.getAccountNo()));
        return fundAccountInfo;
    }

    @Override
    public List<WithdrawAccountInfoResp> selectWithdrawAccts(FundAccountInfoReq req) {
        FundAccountInfo selectParams = new FundAccountInfo();
        selectParams.setUserId(SecurityContextHolder.getUserId());
        selectParams.setAccountCurrency(req.getAccountCurrency());
        selectParams.setAccountOwnerCode(req.getAccountOwnerCode());
        selectParams.setAccountType(req.getAccountType());
        selectParams.setStatus(0L);
        selectParams.setAccountUsage(FundAccountInfoTypeEnum.WITHDRAW.getCode());
        List<FundAccountInfo> fundAccountInfos = fundAccountInfoMapper.selectFundAccountInfoList(selectParams);
        if (CollectionUtils.isEmpty(fundAccountInfos)) {
            return null;
        }
        List<WithdrawAccountInfoResp> withdrawAccountInfos = fundAccountInfos.stream().map(acct -> {
            WithdrawAccountInfoResp item = new WithdrawAccountInfoResp();
            BeanUtils.copyProperties(acct, item);
            return item;
        }).collect(Collectors.toList());
        log.info("查询到账号列表:{}", withdrawAccountInfos);
        return withdrawAccountInfos;
    }
}
