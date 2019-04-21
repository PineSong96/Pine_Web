package com.pine.admin.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.system.entity.SysUserRole;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

    SysUserRole selectByUser(Integer userId);

    List<SysUserRole> queryAllSysUserRole(SysUserRole record);

    List<SysUserRole> querySysUserRolesByPage(Map<String, Object> parameter);

    SysUserRole querySysUserRole(SysUserRole record);

    int delUser(Integer sysUserId);

}
