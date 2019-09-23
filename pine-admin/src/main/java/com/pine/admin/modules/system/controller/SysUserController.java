package com.pine.admin.modules.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.admin.modules.system.entity.SysUser;
import com.pine.admin.modules.system.service.SysService;
import com.pine.admin.modules.system.service.SysUserService;
import com.pine.admin.modules.base.binder.BaseController;
import com.pine.admin.shiro.ShiroUtils;
import com.pine.common.dto.Page;
import com.pine.common.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@RestController
@RequestMapping("system/sysuser")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysService sysService;


    /**
     * <p>获取全部记录。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::all")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(SysUser record) {

        List<SysUser> pp = sysUserService.getSysUserList(record);
        return Result.success(true, pp);
    }


    @PostMapping(value = "/onlinePermission")
    public Result onlinePermission() {
        return Result.success(true, ShiroUtils.getShiroUserInfo().getMenus());
    }

    @PostMapping(value = "/onlineUser")
    public Result onlineUser() {
        return Result.success(true, ShiroUtils.getShiroUserInfo().nullMenus());
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)

    public Result list(@RequestBody List<SysUser> record) {

        List<SysUser> list = record;

        return null;
    }

    /**
     * <p>根据Id。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::id")
    @RequestMapping(value = "/getSysUserEntity", method = RequestMethod.GET)
    public Result getSysUserEntity(@RequestParam(required = false, value = "id") String id) {

        SysUser data = sysUserService.getSysUserById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::getone")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(SysUser record) {

        SysUser data = sysUserService.getSysUser(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::page")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, SysUser record) {

        Map<String, Object> result = new HashMap<>();

        List<SysUser> pp = sysUserService.getSysUserByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(SysUser record) {

        boolean num = sysUserService.createSysUser(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSysUser(SysUser record) {

        boolean num = sysUserService.updateSysUser(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    //@RequiresPermissions(value = "system::sysuser::del")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteSysUser(@RequestParam(required = true, value = "id") String id) {

        boolean num = sysUserService.deleteSysUser(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }
}
