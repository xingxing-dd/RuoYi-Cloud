package com.ruoyi.client.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.client.domain.RechargeBank;
import com.ruoyi.client.service.IRechargeBankService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 充值银行信息Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/bank")
public class RechargeBankController extends BaseController
{
    @Autowired
    private IRechargeBankService rechargeBankService;

    /**
     * 查询充值银行信息列表
     */
    @RequiresPermissions("recharge:bank:list")
    @GetMapping("/list")
    public TableDataInfo list(RechargeBank rechargeBank)
    {
        startPage();
        List<RechargeBank> list = rechargeBankService.selectRechargeBankList(rechargeBank);
        return getDataTable(list);
    }

    /**
     * 导出充值银行信息列表
     */
    @RequiresPermissions("recharge:bank:export")
    @Log(title = "充值银行信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeBank rechargeBank)
    {
        List<RechargeBank> list = rechargeBankService.selectRechargeBankList(rechargeBank);
        ExcelUtil<RechargeBank> util = new ExcelUtil<RechargeBank>(RechargeBank.class);
        util.exportExcel(response, list, "充值银行信息数据");
    }

    /**
     * 获取充值银行信息详细信息
     */
    @RequiresPermissions("recharge:bank:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rechargeBankService.selectRechargeBankById(id));
    }

    /**
     * 新增充值银行信息
     */
    @RequiresPermissions("recharge:bank:add")
    @Log(title = "充值银行信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RechargeBank rechargeBank)
    {
        return toAjax(rechargeBankService.insertRechargeBank(rechargeBank));
    }

    /**
     * 修改充值银行信息
     */
    @RequiresPermissions("recharge:bank:edit")
    @Log(title = "充值银行信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeBank rechargeBank)
    {
        return toAjax(rechargeBankService.updateRechargeBank(rechargeBank));
    }

    /**
     * 删除充值银行信息
     */
    @RequiresPermissions("recharge:bank:remove")
    @Log(title = "充值银行信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rechargeBankService.deleteRechargeBankByIds(ids));
    }
}
