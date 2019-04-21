package com.pine.admin.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.system.entity.SysRole;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysRoleDao extends BaseDao<SysRole> {

    List<SysRole> queryAllSysRole(SysRole record);

    List<SysRole> querySysRolesByPage(Map<String, Object> parameter);

    SysRole querySysRole(SysRole record);

}
