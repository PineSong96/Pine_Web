package com.pine.admin.modules.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.admin.modules.system.service.SysRolePermissionService;
import com.pine.common.dto.Page;
import com.pine.common.dto.Result;
import com.pine.admin.modules.base.binder.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pine.admin.modules.system.entity.SysRolePermission;


/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@RestController
@RequestMapping("system/sysrolepermission")
public class SysRolePermissionController extends BaseController {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;


    /**
     * <p>获取全部记录。</p>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(SysRolePermission record) {

        List<SysRolePermission> pp = sysRolePermissionService.getSysRolePermissionList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @RequestMapping(value = "/getSysRolePermissionEntity", method = RequestMethod.GET)
    public Result getSysRolePermissionEntity(@RequestParam(required = false, value = "id") String id) {

        SysRolePermission data = sysRolePermissionService.getSysRolePermissionById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(SysRolePermission record) {

        SysRolePermission data = sysRolePermissionService.getSysRolePermission(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, SysRolePermission record) {

        Map<String, Object> result = new HashMap<>();

        List<SysRolePermission> pp = sysRolePermissionService.getSysRolePermissionByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(SysRolePermission record) {

        boolean num = sysRolePermissionService.createSysRolePermission(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSysRolePermission(SysRolePermission record) {

        boolean num = sysRolePermissionService.updateSysRolePermission(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteSysRolePermission(@RequestParam(required = true, value = "id") String id) {

        boolean num = sysRolePermissionService.deleteSysRolePermission(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>删除用户所有权限。</p>
     */
    @RequestMapping(value = "/delSysRole", method = RequestMethod.POST)
    public Result delSysRole(@RequestParam(required = true, value = "sysRoleId") String sysRoleId) {

        boolean num = sysRolePermissionService.delSysRole(sysRoleId);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>更新用户权限。</p>
     */
    @RequestMapping(value = "/saveSysRolePermission", method = RequestMethod.POST)
    @ResponseBody
    public Result saveSysRolePermission(@RequestParam(required = true, value = "roleId") String roleId, @RequestParam(required = true, value = "perIds") String perIds) {

        boolean num = sysRolePermissionService.createSysRolePermission(roleId, perIds);
        if (num) {
            return Result.success(true, "创建成功");
        }
        return Result.error("创建失败");
    }


    /**
     * <p>用户权限。</p>
     */
    @RequestMapping(value = "/createSysRolePer", method = RequestMethod.POST)
    @ResponseBody
    public Result saveSysRolePermission(SysRolePermission record) {

        boolean num = sysRolePermissionService.createRolePer(record);
        if (num) {
            return Result.success(true, "创建成功");
        }
        return Result.error("创建失败");
    }


}
