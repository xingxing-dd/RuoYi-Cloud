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
import com.ruoyi.client.domain.ClientIncome;
import com.ruoyi.client.service.IClientIncomeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户收益Controller
 * 
 * @author ruoyi
 * @date 2023-10-21
 */
@RestController
@RequestMapping("/income")
public class ClientIncomeController extends BaseController
{
    @Autowired
    private IClientIncomeService clientIncomeService;

    /**
     * 查询用户收益列表
     */
    @RequiresPermissions("client:income:list")
    @GetMapping("/list")
    public TableDataInfo list(ClientIncome clientIncome)
    {
        startPage();
        List<ClientIncome> list = clientIncomeService.selectClientIncomeList(clientIncome);
        return getDataTable(list);
    }

    /**
     * 导出用户收益列表
     */
    @RequiresPermissions("client:income:export")
    @Log(title = "用户收益", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientIncome clientIncome)
    {
        List<ClientIncome> list = clientIncomeService.selectClientIncomeList(clientIncome);
        ExcelUtil<ClientIncome> util = new ExcelUtil<ClientIncome>(ClientIncome.class);
        util.exportExcel(response, list, "用户收益数据");
    }

    /**
     * 获取用户收益详细信息
     */
    @RequiresPermissions("client:income:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clientIncomeService.selectClientIncomeById(id));
    }

    /**
     * 新增用户收益
     */
    @RequiresPermissions("client:income:add")
    @Log(title = "用户收益", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientIncome clientIncome)
    {
        return toAjax(clientIncomeService.insertClientIncome(clientIncome));
    }

    /**
     * 修改用户收益
     */
    @RequiresPermissions("client:income:edit")
    @Log(title = "用户收益", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientIncome clientIncome)
    {
        return toAjax(clientIncomeService.updateClientIncome(clientIncome));
    }

    /**
     * 删除用户收益
     */
    @RequiresPermissions("client:income:remove")
    @Log(title = "用户收益", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientIncomeService.deleteClientIncomeByIds(ids));
    }
}
