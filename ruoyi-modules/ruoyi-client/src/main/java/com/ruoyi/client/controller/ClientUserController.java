package com.ruoyi.client.controller;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.api.domain.ClientUser;
import com.ruoyi.client.service.IClientUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 账户信息Controller
 * 
 * @author ruoyi
 * @date 2023-08-13
 */
@RestController
@RequestMapping("/client/user")
public class ClientUserController extends BaseController
{
    @Autowired
    private IClientUserService clientUserService;

    /**
     * 查询账户信息列表
     */
    @RequiresPermissions("client:user:list")
    @GetMapping("/list")
    public TableDataInfo list(ClientUser clientUser)
    {
        startPage();
        List<ClientUser> list = clientUserService.selectClientUserList(clientUser);
        return getDataTable(list);
    }

    /**
     * 导出账户信息列表
     */
    @RequiresPermissions("client:user:export")
    @Log(title = "账户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClientUser clientUser)
    {
        List<ClientUser> list = clientUserService.selectClientUserList(clientUser);
        ExcelUtil<ClientUser> util = new ExcelUtil<ClientUser>(ClientUser.class);
        util.exportExcel(response, list, "账户信息数据");
    }

    /**
     * 获取账户信息详细信息
     */
    @RequiresPermissions("client:user:query")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(clientUserService.selectClientUserByUserId(userId));
    }

    /**
     * 新增账户信息
     */
    @RequiresPermissions("client:user:add")
    @Log(title = "账户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClientUser clientUser)
    {
        return toAjax(clientUserService.insertClientUser(clientUser));
    }

    /**
     * 修改账户信息
     */
    @RequiresPermissions("client:user:edit")
    @Log(title = "账户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClientUser clientUser)
    {
        return toAjax(clientUserService.updateClientUser(clientUser));
    }

    /**
     * 删除账户信息
     */
    @RequiresPermissions("client:user:remove")
    @Log(title = "账户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(clientUserService.deleteClientUserByUserIds(userIds));
    }


    @InnerAuth
    @GetMapping("/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source) {
        ClientUser clientUser = clientUserService.selectUserByUserName(username);
        if (StringUtils.isNull(clientUser))
        {
            return R.fail("用户名或密码错误");
        }
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setClientUser(clientUser);
        return R.ok(sysUserVo);
    }

}
