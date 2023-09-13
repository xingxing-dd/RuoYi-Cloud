package com.ruoyi.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.client.domain.vo.RechargeOrderVo;
import com.ruoyi.common.core.context.SecurityContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.ruoyi.client.domain.RechargeOrder;
import com.ruoyi.client.service.IRechargeOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 充值订单Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/order")
public class RechargeOrderController extends BaseController
{
    @Autowired
    private IRechargeOrderService rechargeOrderService;

    /**
     * 查询充值订单列表
     */
    @RequiresPermissions("recharge:order:list")
    @GetMapping("/list")
    public TableDataInfo list(RechargeOrder rechargeOrder)
    {
        startPage();
        List<RechargeOrder> list = rechargeOrderService.selectRechargeOrderList(rechargeOrder);
        return getDataTable(list);
    }

    /**
     * 导出充值订单列表
     */
    @RequiresPermissions("recharge:order:export")
    @Log(title = "充值订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeOrder rechargeOrder)
    {
        List<RechargeOrder> list = rechargeOrderService.selectRechargeOrderList(rechargeOrder);
        ExcelUtil<RechargeOrder> util = new ExcelUtil<RechargeOrder>(RechargeOrder.class);
        util.exportExcel(response, list, "充值订单数据");
    }

    /**
     * 获取充值订单详细信息
     */
    @RequiresPermissions("recharge:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rechargeOrderService.selectRechargeOrderById(id));
    }

    /**
     * 新增充值订单
     */
    @RequiresPermissions("recharge:order:add")
    @Log(title = "充值订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RechargeOrder rechargeOrder)
    {
        return toAjax(rechargeOrderService.insertRechargeOrder(rechargeOrder));
    }

    /**
     * 修改充值订单
     */
    @RequiresPermissions("recharge:order:edit")
    @Log(title = "充值订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeOrder rechargeOrder)
    {
        return toAjax(rechargeOrderService.updateRechargeOrder(rechargeOrder));
    }

    /**
     * 删除充值订单
     */
    @RequiresPermissions("recharge:order:remove")
    @Log(title = "充值订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rechargeOrderService.deleteRechargeOrderByIds(ids));
    }



    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody RechargeOrder rechargeOrder) {
        rechargeOrder.setUserId(SecurityContextHolder.getUserId());
        rechargeOrder.setUserName(SecurityContextHolder.getUserName());
        rechargeOrder.setStatus(0L);
        rechargeOrder.setReceiveAmount(rechargeOrder.getRechargeAmount());
        rechargeOrder.setReceiveCurrency(rechargeOrder.getRechargeCurrency());
        rechargeOrderService.insertRechargeOrder(rechargeOrder);
        return success();
    }

    @PostMapping("/rechargeRecord")
    public AjaxResult rechargeRecord(@RequestBody RechargeOrder rechargeOrder) {
        rechargeOrder.setUserId(SecurityContextHolder.getUserId());
        List<RechargeOrder> rechargeOrders = rechargeOrderService.selectRechargeOrderList(rechargeOrder);
        if (CollectionUtils.isEmpty(rechargeOrders)) {
            return AjaxResult.success();
        }
        List<RechargeOrderVo> rechargeOrderVos = new ArrayList<>();
        for (RechargeOrder order: rechargeOrders) {
            RechargeOrderVo rechargeOrderVo = new RechargeOrderVo();
            BeanUtils.copyProperties(order, rechargeOrderVo);
            rechargeOrderVos.add(rechargeOrderVo);
        }
        return AjaxResult.success(rechargeOrderVos);
    }

}
