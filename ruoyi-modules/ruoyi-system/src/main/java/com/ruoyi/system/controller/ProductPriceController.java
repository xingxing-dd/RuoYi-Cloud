package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.ProductPrice;
import com.ruoyi.system.service.IProductPriceService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 大盘价格数据源Controller
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
@RestController
@RequestMapping("/price")
public class ProductPriceController extends BaseController
{
    @Autowired
    private IProductPriceService productPriceService;

    /**
     * 查询大盘价格数据源列表
     */
    @RequiresPermissions("system:price:list")
    @GetMapping("/list")
    public TableDataInfo list(ProductPrice productPrice)
    {
        startPage();
        List<ProductPrice> list = productPriceService.selectProductPriceList(productPrice);
        return getDataTable(list);
    }

    /**
     * 导出大盘价格数据源列表
     */
    @RequiresPermissions("system:price:export")
    @Log(title = "大盘价格数据源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductPrice productPrice)
    {
        List<ProductPrice> list = productPriceService.selectProductPriceList(productPrice);
        ExcelUtil<ProductPrice> util = new ExcelUtil<ProductPrice>(ProductPrice.class);
        util.exportExcel(response, list, "大盘价格数据源数据");
    }

    /**
     * 获取大盘价格数据源详细信息
     */
    @RequiresPermissions("system:price:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productPriceService.selectProductPriceById(id));
    }

    /**
     * 新增大盘价格数据源
     */
    @RequiresPermissions("system:price:add")
    @Log(title = "大盘价格数据源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductPrice productPrice)
    {
        return toAjax(productPriceService.insertProductPrice(productPrice));
    }

    /**
     * 修改大盘价格数据源
     */
    @RequiresPermissions("system:price:edit")
    @Log(title = "大盘价格数据源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductPrice productPrice)
    {
        return toAjax(productPriceService.updateProductPrice(productPrice));
    }

    /**
     * 删除大盘价格数据源
     */
    @RequiresPermissions("system:price:remove")
    @Log(title = "大盘价格数据源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productPriceService.deleteProductPriceByIds(ids));
    }
}
