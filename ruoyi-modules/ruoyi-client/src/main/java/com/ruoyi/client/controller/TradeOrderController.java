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
import com.ruoyi.client.domain.TradeOrder;
import com.ruoyi.client.service.ITradeOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 交易订单Controller
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
@RestController
@RequestMapping("/trade")
public class TradeOrderController extends BaseController
{
    @Autowired
    private ITradeOrderService tradeOrderService;

    /**
     * 查询交易订单列表
     */
    @RequiresPermissions("fund:trade:list")
    @GetMapping("/list")
    public TableDataInfo list(TradeOrder tradeOrder)
    {
        startPage();
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(tradeOrder);
        return getDataTable(list);
    }

    /**
     * 导出交易订单列表
     */
    @RequiresPermissions("fund:trade:export")
    @Log(title = "交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TradeOrder tradeOrder)
    {
        List<TradeOrder> list = tradeOrderService.selectTradeOrderList(tradeOrder);
        ExcelUtil<TradeOrder> util = new ExcelUtil<TradeOrder>(TradeOrder.class);
        util.exportExcel(response, list, "交易订单数据");
    }

    /**
     * 获取交易订单详细信息
     */
    @RequiresPermissions("fund:trade:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tradeOrderService.selectTradeOrderById(id));
    }

    /**
     * 新增交易订单
     */
    @RequiresPermissions("fund:trade:add")
    @Log(title = "交易订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradeOrder tradeOrder)
    {
        return toAjax(tradeOrderService.insertTradeOrder(tradeOrder));
    }

    /**
     * 修改交易订单
     */
    @RequiresPermissions("fund:trade:edit")
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradeOrder tradeOrder)
    {
        return toAjax(tradeOrderService.updateTradeOrder(tradeOrder));
    }

    /**
     * 删除交易订单
     */
    @RequiresPermissions("fund:trade:remove")
    @Log(title = "交易订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tradeOrderService.deleteTradeOrderByIds(ids));
    }

    public AjaxResult submit(@RequestBody TradeOrder tradeOrder) {

        return AjaxResult.success();
    }

}
