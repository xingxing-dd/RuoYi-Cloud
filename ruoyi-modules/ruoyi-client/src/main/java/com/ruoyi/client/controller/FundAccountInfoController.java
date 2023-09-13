package com.ruoyi.client.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.client.console.FundAccountInfoTypeEnum;
import com.ruoyi.common.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.client.domain.FundAccountInfo;
import com.ruoyi.client.service.IFundAccountInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 账号管理Controller
 * 
 * @author ruoyi
 * @date 2023-08-31
 */
@RestController
@RequestMapping("/account")
public class FundAccountInfoController extends BaseController
{
    @Autowired
    private IFundAccountInfoService fundAccountInfoService;

    /**
     * 查询账号管理列表
     */
    @RequiresPermissions("fund:account:list")
    @GetMapping("/list")
    public TableDataInfo list(FundAccountInfo fundAccountInfo)
    {
        startPage();
        List<FundAccountInfo> list = fundAccountInfoService.selectFundAccountInfoList(fundAccountInfo);
        return getDataTable(list);
    }

    /**
     * 导出账号管理列表
     */
    @RequiresPermissions("fund:account:export")
    @Log(title = "账号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FundAccountInfo fundAccountInfo)
    {
        List<FundAccountInfo> list = fundAccountInfoService.selectFundAccountInfoList(fundAccountInfo);
        ExcelUtil<FundAccountInfo> util = new ExcelUtil<FundAccountInfo>(FundAccountInfo.class);
        util.exportExcel(response, list, "账号管理数据");
    }

    /**
     * 获取账号管理详细信息
     */
    @RequiresPermissions("fund:account:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fundAccountInfoService.selectFundAccountInfoById(id));
    }

    /**
     * 新增账号管理
     */
    @RequiresPermissions("fund:account:add")
    @Log(title = "账号管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FundAccountInfo fundAccountInfo)
    {
        return toAjax(fundAccountInfoService.insertFundAccountInfo(fundAccountInfo));
    }

    /**
     * 修改账号管理
     */
    @RequiresPermissions("fund:account:edit")
    @Log(title = "账号管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FundAccountInfo fundAccountInfo)
    {
        return toAjax(fundAccountInfoService.updateFundAccountInfo(fundAccountInfo));
    }

    /**
     * 删除账号管理
     */
    @RequiresPermissions("fund:account:remove")
    @Log(title = "账号管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fundAccountInfoService.deleteFundAccountInfoByIds(ids));
    }

    @Log(title = "账号管理", businessType = BusinessType.DELETE)
    @PostMapping("/recharge")
    public AjaxResult rechargeAcct(@RequestBody FundAccountInfo fundAccountInfo) {
        fundAccountInfo.setAccountUsage(FundAccountInfoTypeEnum.RECHARGE.getCode());
        List<FundAccountInfo> list = fundAccountInfoService.selectFundAccountInfoList(fundAccountInfo);
        return success(list);
    }

    @Log(title = "账号管理", businessType = BusinessType.DELETE)
    @PostMapping("/withdraw")
    public AjaxResult withdrawAcct(@RequestBody FundAccountInfo fundAccountInfo) {
        fundAccountInfo.setAccountUsage(FundAccountInfoTypeEnum.WITHDRAW.getCode());
        fundAccountInfo.setUserId(SecurityContextHolder.getUserId());
        List<FundAccountInfo> list = fundAccountInfoService.selectFundAccountInfoList(fundAccountInfo);
        return success(list);
    }

    @PostMapping("/bindWithdrawAcct")
    public AjaxResult bindWithdrawAcct(@RequestBody FundAccountInfo fundAccountInfo) {
        fundAccountInfo.setAccountType(FundAccountInfoTypeEnum.WITHDRAW.getCode());
        fundAccountInfo.setUserId(SecurityContextHolder.getUserId());
        fundAccountInfo.setUserName(SecurityContextHolder.getUserName());
        fundAccountInfoService.insertFundAccountInfo(fundAccountInfo);
        return success();
    }

}
