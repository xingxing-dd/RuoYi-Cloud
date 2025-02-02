package com.ruoyi.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.client.controller.resp.EntrustOrderResp;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.redis.service.RedisService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
import com.ruoyi.client.domain.EntrustOrder;
import com.ruoyi.client.service.IEntrustOrderService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

import static com.ruoyi.common.core.constant.MarketConstant.PRODUCT_PRICE_INFO_KEY;

/**
 * 委托订单Controller
 * 
 * @author ruoyi
 * @date 2023-10-07
 */
@RestController
@RequestMapping("/entrust")
public class EntrustOrderController extends BaseController
{
    @Autowired
    private IEntrustOrderService entrustOrderService;

    @Autowired
    private RedisService redisService;

    /**
     * 查询委托订单列表
     */
    @RequiresPermissions("fund:entrust:list")
    @GetMapping("/list")
    public TableDataInfo list(EntrustOrder entrustOrder)
    {
        startPage();
        List<EntrustOrder> list = entrustOrderService.selectEntrustOrderList(entrustOrder);
        return getDataTable(list);
    }

    /**
     * 导出委托订单列表
     */
    @RequiresPermissions("fund:entrust:export")
    @Log(title = "委托订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EntrustOrder entrustOrder)
    {
        List<EntrustOrder> list = entrustOrderService.selectEntrustOrderList(entrustOrder);
        ExcelUtil<EntrustOrder> util = new ExcelUtil<EntrustOrder>(EntrustOrder.class);
        util.exportExcel(response, list, "委托订单数据");
    }

    /**
     * 获取委托订单详细信息
     */
    @RequiresPermissions("fund:entrust:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(entrustOrderService.selectEntrustOrderById(id));
    }

    /**
     * 新增委托订单
     */
    @RequiresPermissions("fund:entrust:add")
    @Log(title = "委托订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EntrustOrder entrustOrder)
    {
        return toAjax(entrustOrderService.insertEntrustOrder(entrustOrder));
    }

    /**
     * 修改委托订单
     */
    @RequiresPermissions("fund:entrust:edit")
    @Log(title = "委托订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EntrustOrder entrustOrder)
    {
        return toAjax(entrustOrderService.updateEntrustOrder(entrustOrder));
    }

    /**
     * 删除委托订单
     */
    @RequiresPermissions("fund:entrust:remove")
    @Log(title = "委托订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(entrustOrderService.deleteEntrustOrderByIds(ids));
    }

    @PostMapping("/submit")
    public AjaxResult submit(@RequestBody EntrustOrder entrustOrder) {

        return AjaxResult.success(entrustOrderService.submit(entrustOrder));
    }

    @PostMapping("/orderList")
    public AjaxResult orderList(@RequestBody EntrustOrder entrustOrder) {
        entrustOrder.setUserId(SecurityContextHolder.getUserId());
        List<EntrustOrder> entrustOrders = entrustOrderService.selectEntrustOrderList(entrustOrder);
        if (CollectionUtils.isEmpty(entrustOrders)) {
            return AjaxResult.success(new ArrayList<>());
        }
        List<EntrustOrderResp> entrustOrderResps = new ArrayList<>();
        for (EntrustOrder order: entrustOrders) {
            EntrustOrderResp entrustOrderResp = new EntrustOrderResp();
            BeanUtils.copyProperties(order, entrustOrderResp);
            String productPriceCacheKey = String.format(PRODUCT_PRICE_INFO_KEY, entrustOrderResp.getProductCode(), DateUtils.dateTime());
            JSONObject price = redisService.getCacheObject(productPriceCacheKey);
            if (price != null && price.containsKey("currentPrice")) {
                entrustOrderResp.setCurrentPrice(price.getBigDecimal("currentPrice"));
            }
            entrustOrderResps.add(entrustOrderResp);
        }
        return AjaxResult.success(entrustOrderResps);
    }

}
