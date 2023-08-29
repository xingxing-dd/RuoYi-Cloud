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
import com.ruoyi.client.domain.ClientUserWalletFlow;
import com.ruoyi.client.service.IClientUserWalletFlowService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户钱包流水Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/wallet-flow")
public class ClientUserWalletFlowController extends BaseController
{
    @Autowired
    private IClientUserWalletFlowService clientUserWalletFlowService;

    /**
     * 查询用户钱包流水列表
     */
    @RequiresPermissions("client:wallet-flow:list")
    @GetMapping("/list")
    public TableDataInfo list(ClientUserWalletFlow clientUserWalletFlow)
    {
        startPage();
        List<ClientUserWalletFlow> list = clientUserWalletFlowService.selectClientUserWalletFlowList(clientUserWalletFlow);
        return getDataTable(list);
    }

    /**
     * 导出用户钱包流水列表
     */
    @RequiresPermissions("client:wallet-flow:export")
    @Log(title = "用户钱包流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientUserWalletFlow clientUserWalletFlow)
    {
        List<ClientUserWalletFlow> list = clientUserWalletFlowService.selectClientUserWalletFlowList(clientUserWalletFlow);
        ExcelUtil<ClientUserWalletFlow> util = new ExcelUtil<ClientUserWalletFlow>(ClientUserWalletFlow.class);
        util.exportExcel(response, list, "用户钱包流水数据");
    }

    /**
     * 获取用户钱包流水详细信息
     */
    @RequiresPermissions("client:wallet-flow:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clientUserWalletFlowService.selectClientUserWalletFlowById(id));
    }

    /**
     * 新增用户钱包流水
     */
    @RequiresPermissions("client:wallet-flow:add")
    @Log(title = "用户钱包流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientUserWalletFlow clientUserWalletFlow)
    {
        return toAjax(clientUserWalletFlowService.insertClientUserWalletFlow(clientUserWalletFlow));
    }

    /**
     * 修改用户钱包流水
     */
    @RequiresPermissions("client:wallet-flow:edit")
    @Log(title = "用户钱包流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientUserWalletFlow clientUserWalletFlow)
    {
        return toAjax(clientUserWalletFlowService.updateClientUserWalletFlow(clientUserWalletFlow));
    }

    /**
     * 删除用户钱包流水
     */
    @RequiresPermissions("client:wallet-flow:remove")
    @Log(title = "用户钱包流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientUserWalletFlowService.deleteClientUserWalletFlowByIds(ids));
    }
}
