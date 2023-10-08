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
import com.ruoyi.client.domain.ClientUserAuth;
import com.ruoyi.client.service.IClientUserAuthService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 实名认证Controller
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
@RestController
@RequestMapping("/auth")
public class ClientUserAuthController extends BaseController
{
    @Autowired
    private IClientUserAuthService clientUserAuthService;

    /**
     * 查询实名认证列表
     */
    @RequiresPermissions("client:auth:list")
    @GetMapping("/list")
    public TableDataInfo list(ClientUserAuth clientUserAuth)
    {
        startPage();
        List<ClientUserAuth> list = clientUserAuthService.selectClientUserAuthList(clientUserAuth);
        return getDataTable(list);
    }

    /**
     * 导出实名认证列表
     */
    @RequiresPermissions("client:auth:export")
    @Log(title = "实名认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientUserAuth clientUserAuth)
    {
        List<ClientUserAuth> list = clientUserAuthService.selectClientUserAuthList(clientUserAuth);
        ExcelUtil<ClientUserAuth> util = new ExcelUtil<ClientUserAuth>(ClientUserAuth.class);
        util.exportExcel(response, list, "实名认证数据");
    }

    /**
     * 获取实名认证详细信息
     */
    @RequiresPermissions("client:auth:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clientUserAuthService.selectClientUserAuthById(id));
    }

    /**
     * 新增实名认证
     */
    @RequiresPermissions("client:auth:add")
    @Log(title = "实名认证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientUserAuth clientUserAuth)
    {
        return toAjax(clientUserAuthService.insertClientUserAuth(clientUserAuth));
    }

    /**
     * 修改实名认证
     */
    @RequiresPermissions("client:auth:edit")
    @Log(title = "实名认证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientUserAuth clientUserAuth)
    {
        return toAjax(clientUserAuthService.updateClientUserAuth(clientUserAuth));
    }

    /**
     * 删除实名认证
     */
    @RequiresPermissions("client:auth:remove")
    @Log(title = "实名认证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientUserAuthService.deleteClientUserAuthByIds(ids));
    }

    @PostMapping("/detail")
    public AjaxResult detail() {
        return AjaxResult.success(clientUserAuthService.detail());
    }

    @PostMapping("/submitAuth")
    public AjaxResult submitAuth(@RequestBody ClientUserAuth clientUserAuth) {
        return AjaxResult.success(clientUserAuthService.submitAuth(clientUserAuth));
    }
}
