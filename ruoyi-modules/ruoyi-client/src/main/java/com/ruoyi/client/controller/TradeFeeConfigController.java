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
import com.ruoyi.client.domain.TradeFeeConfig;
import com.ruoyi.client.service.ITradeFeeConfigService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 费用配置Controller
 * 
 * @author ruoyi
 * @date 2023-10-05
 */
@RestController
@RequestMapping("/config")
public class TradeFeeConfigController extends BaseController
{
    @Autowired
    private ITradeFeeConfigService tradeFeeConfigService;

    /**
     * 查询费用配置列表
     */
    @RequiresPermissions("fund:config:list")
    @GetMapping("/list")
    public TableDataInfo list(TradeFeeConfig tradeFeeConfig)
    {
        startPage();
        List<TradeFeeConfig> list = tradeFeeConfigService.selectTradeFeeConfigList(tradeFeeConfig);
        return getDataTable(list);
    }

    /**
     * 导出费用配置列表
     */
    @RequiresPermissions("fund:config:export")
    @Log(title = "费用配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TradeFeeConfig tradeFeeConfig)
    {
        List<TradeFeeConfig> list = tradeFeeConfigService.selectTradeFeeConfigList(tradeFeeConfig);
        ExcelUtil<TradeFeeConfig> util = new ExcelUtil<TradeFeeConfig>(TradeFeeConfig.class);
        util.exportExcel(response, list, "费用配置数据");
    }

    /**
     * 获取费用配置详细信息
     */
    @RequiresPermissions("fund:config:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tradeFeeConfigService.selectTradeFeeConfigById(id));
    }

    /**
     * 新增费用配置
     */
    @RequiresPermissions("fund:config:add")
    @Log(title = "费用配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradeFeeConfig tradeFeeConfig)
    {
        return toAjax(tradeFeeConfigService.insertTradeFeeConfig(tradeFeeConfig));
    }

    /**
     * 修改费用配置
     */
    @RequiresPermissions("fund:config:edit")
    @Log(title = "费用配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradeFeeConfig tradeFeeConfig)
    {
        return toAjax(tradeFeeConfigService.updateTradeFeeConfig(tradeFeeConfig));
    }

    /**
     * 删除费用配置
     */
    @RequiresPermissions("fund:config:remove")
    @Log(title = "费用配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tradeFeeConfigService.deleteTradeFeeConfigByIds(ids));
    }
}
