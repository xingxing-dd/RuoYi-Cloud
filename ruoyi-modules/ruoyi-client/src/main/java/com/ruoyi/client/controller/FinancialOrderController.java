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
import com.ruoyi.client.domain.FinancialOrder;
import com.ruoyi.client.service.IFinancialOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 余额宝订单Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/order")
public class FinancialOrderController extends BaseController
{
    @Autowired
    private IFinancialOrderService financialOrderService;

    /**
     * 查询余额宝订单列表
     */
    @RequiresPermissions("financial:order:list")
    @GetMapping("/list")
    public TableDataInfo list(FinancialOrder financialOrder)
    {
        startPage();
        List<FinancialOrder> list = financialOrderService.selectFinancialOrderList(financialOrder);
        return getDataTable(list);
    }

    /**
     * 导出余额宝订单列表
     */
    @RequiresPermissions("financial:order:export")
    @Log(title = "余额宝订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialOrder financialOrder)
    {
        List<FinancialOrder> list = financialOrderService.selectFinancialOrderList(financialOrder);
        ExcelUtil<FinancialOrder> util = new ExcelUtil<FinancialOrder>(FinancialOrder.class);
        util.exportExcel(response, list, "余额宝订单数据");
    }

    /**
     * 获取余额宝订单详细信息
     */
    @RequiresPermissions("financial:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(financialOrderService.selectFinancialOrderById(id));
    }

    /**
     * 新增余额宝订单
     */
    @RequiresPermissions("financial:order:add")
    @Log(title = "余额宝订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialOrder financialOrder)
    {
        return toAjax(financialOrderService.insertFinancialOrder(financialOrder));
    }

    /**
     * 修改余额宝订单
     */
    @RequiresPermissions("financial:order:edit")
    @Log(title = "余额宝订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialOrder financialOrder)
    {
        return toAjax(financialOrderService.updateFinancialOrder(financialOrder));
    }

    /**
     * 删除余额宝订单
     */
    @RequiresPermissions("financial:order:remove")
    @Log(title = "余额宝订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(financialOrderService.deleteFinancialOrderByIds(ids));
    }
}
