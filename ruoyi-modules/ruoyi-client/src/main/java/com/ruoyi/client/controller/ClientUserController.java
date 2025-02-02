package com.ruoyi.client.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.client.domain.ClientUser;
import com.ruoyi.client.domain.ClientUserWallet;
import com.ruoyi.client.domain.vo.ClientUserInfoVo;
import com.ruoyi.client.service.IClientUserWalletService;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.annotation.InnerAuth;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
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
@RequestMapping("/user")
public class ClientUserController extends BaseController
{
    @Autowired
    private IClientUserService clientUserService;

    @Autowired
    private IClientUserWalletService clientUserWalletService;

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
        logger.info("登录用户名:{}", username);
        ClientUser clientUser = clientUserService.selectUserByUserName(username);
        logger.info("查询到账户信息:{}", clientUser);
        if (StringUtils.isNull(clientUser))
        {
            return R.fail("用户名或密码错误");
        }

        LoginUser sysUserVo = new LoginUser();
        com.ruoyi.system.api.domain.ClientUser user = new com.ruoyi.system.api.domain.ClientUser();
        BeanUtils.copyProperties(clientUser, user);
        sysUserVo.setClientUser(user);
        return R.ok(sysUserVo);
    }

    @PostMapping("/detail")
    public R<ClientUserInfoVo> getClientUserInfo() {
        Long userId = SecurityContextHolder.getUserId();
        if (userId == null) {
            return R.fail("Not login!");
        }
        ClientUser clientUser = clientUserService.selectClientUserByUserId(userId);
        if (StringUtils.isNull(clientUser))
        {
            return R.fail("User is not exists!");
        }
        ClientUserWallet clientUserWallet = clientUserWalletService.selectClientUserWalletByUserId(clientUser.getUserId());
        ClientUserInfoVo clientUserInfoVo = new ClientUserInfoVo();
        BeanUtils.copyProperties(clientUser, clientUserInfoVo);
        if (clientUserWallet != null) {
            BeanUtils.copyProperties(clientUserWallet, clientUserInfoVo);
        }
        return R.ok(clientUserInfoVo);
    }

    @InnerAuth
    @PostMapping("/register")
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> register(@RequestBody ClientUser clientUser) {
        if (clientUser == null || StringUtils.isAllBlank(clientUser.getUserName(), clientUser.getPassword())) {
            return R.fail("Username or password must not blank!");
        }
        ClientUser existUser = clientUserService.selectUserByUserName(clientUser.getUserName());
        if (existUser != null) {
            return R.fail("Username has exists!");
        }
        boolean registerUserResult = clientUserService.register(clientUser);
        logger.info("注册账号结果:{}", registerUserResult);
        if (!registerUserResult) {
            return R.fail("Unknown exception");
        }
        boolean createUserWalletResult = clientUserWalletService.createUserWallet(clientUser.getUserId(), clientUser.getUserName());
        logger.info("创建钱包账户结果:{}", createUserWalletResult);
        if (!createUserWalletResult) {
            return R.fail("Unknown exception");
        }
        return R.ok(true);
    }

    @PostMapping("/generateInviteUrl")
    public AjaxResult generateInviteUrl() {
        return AjaxResult.success(clientUserService.generateInviteUrl());
    }

}
