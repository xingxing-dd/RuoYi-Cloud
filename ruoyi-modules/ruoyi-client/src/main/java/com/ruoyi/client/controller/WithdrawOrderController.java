package com.ruoyi.client.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.client.controller.req.WithdrawCalculateReq;
import com.ruoyi.client.controller.req.WithdrawOrderSubmitReq;
import com.ruoyi.client.domain.vo.WithdrawOrderVo;
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
import com.ruoyi.client.domain.WithdrawOrder;
import com.ruoyi.client.service.IWithdrawOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 提现订单Controller
 * 
 * @author ruoyi
 * @date 2023-09-03
 */
@RestController
@RequestMapping("/withdraw")
public class WithdrawOrderController extends BaseController
{
    @Autowired
    private IWithdrawOrderService withdrawOrderService;

    /**
     * 查询提现订单列表
     */
    @RequiresPermissions("fund:withdraw:list")
    @GetMapping("/list")
    public TableDataInfo list(WithdrawOrder withdrawOrder)
    {
        startPage();
        List<WithdrawOrder> list = withdrawOrderService.selectWithdrawOrderList(withdrawOrder);
        return getDataTable(list);
    }

    /**
     * 导出提现订单列表
     */
    @RequiresPermissions("fund:withdraw:export")
    @Log(title = "提现订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WithdrawOrder withdrawOrder)
    {
        List<WithdrawOrder> list = withdrawOrderService.selectWithdrawOrderList(withdrawOrder);
        ExcelUtil<WithdrawOrder> util = new ExcelUtil<WithdrawOrder>(WithdrawOrder.class);
        util.exportExcel(response, list, "提现订单数据");
    }

    /**
     * 获取提现订单详细信息
     */
    @RequiresPermissions("fund:withdraw:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(withdrawOrderService.selectWithdrawOrderById(id));
    }

    /**
     * 新增提现订单
     */
    @RequiresPermissions("fund:withdraw:add")
    @Log(title = "提现订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WithdrawOrder withdrawOrder)
    {
        return toAjax(withdrawOrderService.insertWithdrawOrder(withdrawOrder));
    }

    /**
     * 修改提现订单
     */
    @RequiresPermissions("fund:withdraw:edit")
    @Log(title = "提现订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WithdrawOrder withdrawOrder)
    {
        return toAjax(withdrawOrderService.updateWithdrawOrder(withdrawOrder));
    }

    /**
     * 删除提现订单
     */
    @RequiresPermissions("fund:withdraw:remove")
    @Log(title = "提现订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(withdrawOrderService.deleteWithdrawOrderByIds(ids));
    }

    @PostMapping("/calculate")
    public AjaxResult calculate(@RequestBody WithdrawCalculateReq withdrawCalculateReq) {
        return success(withdrawOrderService.calculate(withdrawCalculateReq));
    }

    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody WithdrawOrderSubmitReq withdrawOrderSubmitReq) {
        withdrawOrderService.submitWithdrawOrder(withdrawOrderSubmitReq);
        return success();
    }

    @PostMapping("/withdrawRecord")
    public AjaxResult withdrawRecord(@RequestBody WithdrawOrder withdrawOrder) {
        withdrawOrder.setUserId(SecurityContextHolder.getUserId());
        List<WithdrawOrder> withdrawOrders = withdrawOrderService.selectWithdrawOrderList(withdrawOrder);
        if (CollectionUtils.isEmpty(withdrawOrders)) {
            return AjaxResult.success();
        }
        List<WithdrawOrderVo> withdrawOrderVos = new ArrayList<>();
        for (WithdrawOrder order: withdrawOrders) {
            WithdrawOrderVo withdrawOrderVo = new WithdrawOrderVo();
            BeanUtils.copyProperties(order, withdrawOrderVo);
            withdrawOrderVos.add(withdrawOrderVo);
        }
        return AjaxResult.success(withdrawOrders);
    }

}
