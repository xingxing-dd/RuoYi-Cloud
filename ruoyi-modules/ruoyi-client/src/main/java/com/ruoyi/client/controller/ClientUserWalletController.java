package com.ruoyi.client.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.client.domain.ClientUser;
import com.ruoyi.client.service.IClientUserService;
import com.ruoyi.common.core.domain.R;
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
import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 用户资产Controller
 * 
 * @author ruoyi
 * @date 2023-08-29
 */
@RestController
@RequestMapping("/wallet")
public class ClientUserWalletController extends BaseController
{
    @Autowired
    private IClientUserWalletService clientUserWalletService;

    @Autowired
    private IClientUserService clientUserService;

    /**
     * 查询用户资产列表
     */
    @RequiresPermissions("client:wallet:list")
    @GetMapping("/list")
    public TableDataInfo list(ClientUserWallet clientUserWallet)
    {
        startPage();
        List<ClientUserWallet> list = clientUserWalletService.selectClientUserWalletList(clientUserWallet);
        for (ClientUserWallet wallet: list) {
            ClientUser clientUser = clientUserService.selectClientUserByUserId(wallet.getUserId());
            if (clientUser == null) {
                continue;
            }
            wallet.setUserName(clientUser.getUserName());
        }
        return getDataTable(list);
    }

    /**
     * 导出用户资产列表
     */
    @RequiresPermissions("client:wallet:export")
    @Log(title = "用户资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientUserWallet clientUserWallet)
    {
        List<ClientUserWallet> list = clientUserWalletService.selectClientUserWalletList(clientUserWallet);
        ExcelUtil<ClientUserWallet> util = new ExcelUtil<ClientUserWallet>(ClientUserWallet.class);
        util.exportExcel(response, list, "用户资产数据");
    }

    /**
     * 获取用户资产详细信息
     */
    @RequiresPermissions("client:wallet:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(clientUserWalletService.selectClientUserWalletById(id));
    }

    /**
     * 新增用户资产
     */
    @RequiresPermissions("client:wallet:add")
    @Log(title = "用户资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientUserWallet clientUserWallet)
    {
        return toAjax(clientUserWalletService.insertClientUserWallet(clientUserWallet));
    }

    /**
     * 修改用户资产
     */
    @RequiresPermissions("client:wallet:edit")
    @Log(title = "用户资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientUserWallet clientUserWallet)
    {
        return toAjax(clientUserWalletService.updateClientUserWallet(clientUserWallet));
    }

    /**
     * 删除用户资产
     */
    @RequiresPermissions("client:wallet:remove")
    @Log(title = "用户资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientUserWalletService.deleteClientUserWalletByIds(ids));
    }

    /**
     * 获取用户钱包数据
     * @param clientUserWallet
     * @return
     */
    @PostMapping("/detail")
    public R<ClientUserWallet> getUserWallet(@RequestBody ClientUserWallet clientUserWallet) {
        return R.ok(clientUserWalletService.getUserWallet(clientUserWallet.getUserId()));
    }

}
