package com.pine.admin.modules.system.service;

import com.pine.admin.modules.system.entity.SysRole;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysRoleService {

    List<SysRole> getSysRoleList(SysRole record);

    List<SysRole> getSysRoleByPage(Page page, SysRole record);

    SysRole getSysRoleById(String id);

    SysRole getSysRole(SysRole record);

    boolean createSysRole(SysRole record);

    boolean deleteSysRole(String id);

    boolean updateSysRole(SysRole record);

}

