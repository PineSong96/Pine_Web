package com.pine.admin.modules.system.service;

import com.pine.admin.modules.base.binder.BaseService;
import com.pine.admin.modules.system.entity.SysPermission;

import java.util.List;

import com.pine.common.dto.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysPermissionService {

    List<SysPermission> getSysPermissionList(SysPermission record);

    List<SysPermission> getSysPermissionByPage(Page page, SysPermission record);

    SysPermission getSysPermissionById(String id);

    SysPermission getSysPermission(SysPermission record);

    boolean createSysPermission(SysPermission record);

    boolean deleteSysPermission(String id);

    boolean updateSysPermission(SysPermission record);

    List<SysPermission> getSysMenu(HttpServletRequest request, HttpServletResponse response);

}

