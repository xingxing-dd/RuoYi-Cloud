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
import com.ruoyi.market.domain.ProductConfig;
import com.ruoyi.market.service.IProductConfigService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 产品配置Controller
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
@RestController
@RequestMapping("/config")
public class ProductConfigController extends BaseController
{
    @Autowired
    private IProductConfigService productConfigService;

    /**
     * 查询产品配置列表
     */
    @RequiresPermissions("market:config:list")
    @GetMapping("/list")
    public TableDataInfo list(ProductConfig productConfig)
    {
        startPage();
        List<ProductConfig> list = productConfigService.selectProductConfigList(productConfig);
        return getDataTable(list);
    }

    /**
     * 导出产品配置列表
     */
    @RequiresPermissions("market:config:export")
    @Log(title = "产品配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductConfig productConfig)
    {
        List<ProductConfig> list = productConfigService.selectProductConfigList(productConfig);
        ExcelUtil<ProductConfig> util = new ExcelUtil<ProductConfig>(ProductConfig.class);
        util.exportExcel(response, list, "产品配置数据");
    }

    /**
     * 获取产品配置详细信息
     */
    @RequiresPermissions("market:config:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productConfigService.selectProductConfigById(id));
    }

    /**
     * 新增产品配置
     */
    @RequiresPermissions("market:config:add")
    @Log(title = "产品配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductConfig productConfig)
    {
        return toAjax(productConfigService.insertProductConfig(productConfig));
    }

    /**
     * 修改产品配置
     */
    @RequiresPermissions("market:config:edit")
    @Log(title = "产品配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductConfig productConfig)
    {
        return toAjax(productConfigService.updateProductConfig(productConfig));
    }

    /**
     * 删除产品配置
     */
    @RequiresPermissions("market:config:remove")
    @Log(title = "产品配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productConfigService.deleteProductConfigByIds(ids));
    }
}
