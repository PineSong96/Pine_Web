package com.pine.admin.modules.system.service;

import com.pine.admin.modules.system.entity.SysUserRole;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysUserRoleService {

    List<SysUserRole> getSysUserRoleList(SysUserRole record);

    List<SysUserRole> getSysUserRoleByPage(Page page, SysUserRole record);

    SysUserRole getSysUserRoleById(String id);

    SysUserRole getSysUserRole(SysUserRole record);

    boolean createSysUserRole(SysUserRole record);

    boolean deleteSysUserRole(String id);

    boolean updateSysUserRole(SysUserRole record);

    boolean delUser(String sysUserId);

    /**
     * 删除原来的角色
     * 创建新角色
     */

    boolean createSysRolePermission(String userId, String roleIds);

    SysUserRole selectByUser(Integer userId);
}

