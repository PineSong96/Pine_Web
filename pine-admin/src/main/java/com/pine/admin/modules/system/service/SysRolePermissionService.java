package com.pine.admin.modules.system.service;

import com.pine.admin.modules.system.entity.SysRolePermission;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysRolePermissionService {

    List<SysRolePermission> getSysRolePermissionList(SysRolePermission record);

    List<SysRolePermission> getSysRolePermissionByPage(Page page, SysRolePermission record);

    SysRolePermission getSysRolePermissionById(String id);

    SysRolePermission getSysRolePermission(SysRolePermission record);

    boolean createSysRolePermission(SysRolePermission record);

    boolean createSysRolePermission(String roleId, String perIds);


    boolean deleteSysRolePermission(String id);

    boolean updateSysRolePermission(SysRolePermission record);

    boolean delSysRole(String sysRoleId);

    boolean createRolePer(SysRolePermission record);
}

