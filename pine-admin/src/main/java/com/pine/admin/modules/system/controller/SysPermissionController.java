package com.pine.admin.modules.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.service.SysPermissionService;
import com.pine.admin.modules.system.service.SysService;
import com.pine.common.dto.Page;
import com.pine.common.dto.Result;
import com.pine.admin.modules.base.binder.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@RestController
@RequestMapping("system/syspermission")
@Api(value = "用户权限", tags = {"用户权限" })
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysService sysService;


    /**
     * <p>获取全部记录。</p>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(SysPermission record) {

        List<SysPermission> pp = sysPermissionService.getSysPermissionList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @RequestMapping(value = "/getSysPermissionEntity", method = RequestMethod.GET)
    public Result getSysPermissionEntity(@RequestParam(required = false, value = "id") String id) {

        SysPermission data = sysPermissionService.getSysPermissionById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(SysPermission record) {

        SysPermission data = sysPermissionService.getSysPermission(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, SysPermission record) {

        Map<String, Object> result = new HashMap<>();

        List<SysPermission> pp = sysPermissionService.getSysPermissionByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(SysPermission record) {

        boolean num = sysPermissionService.createSysPermission(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSysPermission(SysPermission record) {

        boolean num = sysPermissionService.updateSysPermission(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteSysPermission(@RequestParam(required = true, value = "id") String id) {

        boolean num = sysPermissionService.deleteSysPermission(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>获得用户菜单。</p>
     */
    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.POST)
    public Result getRoleMenu(HttpServletRequest request, HttpServletResponse response) {

        List<SysPermission> record = sysPermissionService.getSysMenu(request, response);
        return Result.success(true, record);
    }

    /**
     * <p>获得用户菜单。</p>
     */
    @RequestMapping(value = "/getRoleMenuById", method = RequestMethod.POST)

    public Result getRoleMenuById(Integer userId) {

        List<SysPermission> record = sysService.findMenuListByUserId(userId);

        return Result.success(true, record);
    }
}
