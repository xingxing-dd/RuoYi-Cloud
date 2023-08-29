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
import com.ruoyi.client.domain.RechargeNetwork;
import com.ruoyi.client.service.IRechargeNetworkService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 充币地址Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/network")
public class RechargeNetworkController extends BaseController
{
    @Autowired
    private IRechargeNetworkService rechargeNetworkService;

    /**
     * 查询充币地址列表
     */
    @RequiresPermissions("recharge:network:list")
    @GetMapping("/list")
    public TableDataInfo list(RechargeNetwork rechargeNetwork)
    {
        startPage();
        List<RechargeNetwork> list = rechargeNetworkService.selectRechargeNetworkList(rechargeNetwork);
        return getDataTable(list);
    }

    /**
     * 导出充币地址列表
     */
    @RequiresPermissions("recharge:network:export")
    @Log(title = "充币地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeNetwork rechargeNetwork)
    {
        List<RechargeNetwork> list = rechargeNetworkService.selectRechargeNetworkList(rechargeNetwork);
        ExcelUtil<RechargeNetwork> util = new ExcelUtil<RechargeNetwork>(RechargeNetwork.class);
        util.exportExcel(response, list, "充币地址数据");
    }

    /**
     * 获取充币地址详细信息
     */
    @RequiresPermissions("recharge:network:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rechargeNetworkService.selectRechargeNetworkById(id));
    }

    /**
     * 新增充币地址
     */
    @RequiresPermissions("recharge:network:add")
    @Log(title = "充币地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RechargeNetwork rechargeNetwork)
    {
        return toAjax(rechargeNetworkService.insertRechargeNetwork(rechargeNetwork));
    }

    /**
     * 修改充币地址
     */
    @RequiresPermissions("recharge:network:edit")
    @Log(title = "充币地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeNetwork rechargeNetwork)
    {
        return toAjax(rechargeNetworkService.updateRechargeNetwork(rechargeNetwork));
    }

    /**
     * 删除充币地址
     */
    @RequiresPermissions("recharge:network:remove")
    @Log(title = "充币地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rechargeNetworkService.deleteRechargeNetworkByIds(ids));
    }
}
