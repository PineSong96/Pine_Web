package com.pine.admin.modules.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.admin.modules.system.service.SysRoleService;
import com.pine.common.dto.Page;
import com.pine.common.dto.Result;
import com.pine.admin.modules.base.binder.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pine.admin.modules.system.entity.SysRole;


/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@RestController
@RequestMapping("system/sysrole")
@Api(value = "用户角色", tags = {"用户角色" })
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * <p>获取全部记录。</p>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(SysRole record) {

        List<SysRole> pp = sysRoleService.getSysRoleList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @RequestMapping(value = "/getSysRoleEntity", method = RequestMethod.GET)
    public Result getSysRoleEntity(@RequestParam(required = false, value = "id") String id) {

        SysRole data = sysRoleService.getSysRoleById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(SysRole record) {

        SysRole data = sysRoleService.getSysRole(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, SysRole record) {

        Map<String, Object> result = new HashMap<>();

        List<SysRole> pp = sysRoleService.getSysRoleByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(SysRole record) {

        boolean num = sysRoleService.createSysRole(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSysRole(SysRole record) {

        boolean num = sysRoleService.updateSysRole(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result deleteSysRole(@RequestParam(required = true, value = "id") String id) {

        boolean num = sysRoleService.deleteSysRole(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }


}
