package com.ruoyi.market.controller;

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
import com.ruoyi.market.domain.FinancialInfo;
import com.ruoyi.market.service.IFinancialInfoService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 理财产品Controller
 * 
 * @author ruoyi
 * @date 2023-10-19
 */
@RestController
@RequestMapping("/financial")
public class FinancialInfoController extends BaseController
{
    @Autowired
    private IFinancialInfoService financialInfoService;

    /**
     * 查询理财产品列表
     */
    @RequiresPermissions("market:financial:list")
    @GetMapping("/list")
    public TableDataInfo list(FinancialInfo financialInfo)
    {
        startPage();
        List<FinancialInfo> list = financialInfoService.selectFinancialInfoList(financialInfo);
        return getDataTable(list);
    }

    /**
     * 导出理财产品列表
     */
    @RequiresPermissions("market:financial:export")
    @Log(title = "理财产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialInfo financialInfo)
    {
        List<FinancialInfo> list = financialInfoService.selectFinancialInfoList(financialInfo);
        ExcelUtil<FinancialInfo> util = new ExcelUtil<FinancialInfo>(FinancialInfo.class);
        util.exportExcel(response, list, "理财产品数据");
    }

    /**
     * 获取理财产品详细信息
     */
    @RequiresPermissions("market:financial:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(financialInfoService.selectFinancialInfoById(id));
    }

    /**
     * 新增理财产品
     */
    @RequiresPermissions("market:financial:add")
    @Log(title = "理财产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialInfo financialInfo)
    {
        return toAjax(financialInfoService.insertFinancialInfo(financialInfo));
    }

    /**
     * 修改理财产品
     */
    @RequiresPermissions("market:financial:edit")
    @Log(title = "理财产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialInfo financialInfo)
    {
        return toAjax(financialInfoService.updateFinancialInfo(financialInfo));
    }

    /**
     * 删除理财产品
     */
    @RequiresPermissions("market:financial:remove")
    @Log(title = "理财产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(financialInfoService.deleteFinancialInfoByIds(ids));
    }

    @PostMapping("/queryProduct")
    public AjaxResult queryProduct(@RequestBody FinancialInfo financialInfo) {
        return AjaxResult.success(financialInfoService.selectFinancialInfoList(financialInfo));
    }

}
