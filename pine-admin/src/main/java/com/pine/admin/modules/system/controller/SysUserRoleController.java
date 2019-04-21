package com.pine.admin.modules.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.admin.modules.system.entity.SysUserRole;
import com.pine.admin.modules.system.service.SysUserRoleService;
import com.pine.common.dto.Page;
import com.pine.common.dto.Result;
import com.pine.admin.modules.base.binder.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@RestController
@RequestMapping("system/sysuserrole")
public class SysUserRoleController extends BaseController {

    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
     * <p>获取全部记录。</p>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(SysUserRole record) {

        List<SysUserRole> pp = sysUserRoleService.getSysUserRoleList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @RequestMapping(value = "/getSysUserRoleEntity", method = RequestMethod.GET)
    public Result getSysUserRoleEntity(@RequestParam(required = false, value = "id") String id) {

        SysUserRole data = sysUserRoleService.getSysUserRoleById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(SysUserRole record) {

        SysUserRole data = sysUserRoleService.getSysUserRole(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, SysUserRole record) {

        Map<String, Object> result = new HashMap<>();

        List<SysUserRole> pp = sysUserRoleService.getSysUserRoleByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(SysUserRole record) {

        boolean num = sysUserRoleService.createSysUserRole(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSysUserRole(SysUserRole record) {

        boolean num = sysUserRoleService.updateSysUserRole(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteSysUserRole(@RequestParam(required = true, value = "id") String id) {

        boolean num = sysUserRoleService.deleteSysUserRole(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public Result delUser(@RequestParam(required = true, value = "sysUserId") String sysUserId) {

        boolean num = sysUserRoleService.delUser(sysUserId);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>更新用户角色。</p>
     */
    @RequestMapping(value = "/saveSysRoleUser", method = RequestMethod.POST)
    public Result saveSysRoleUser(@RequestParam(required = true, value = "userId") String userId, @RequestParam(required = true, value = "roleId") String roleId) {


        boolean num = sysUserRoleService.createSysRolePermission(userId, roleId);
        if (num) {
            return Result.success(true, "创建成功");
        }
        return Result.error("创建失败");
    }
}
