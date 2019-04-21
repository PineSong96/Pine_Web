package com.pine.admin.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.system.entity.SysPermission;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysPermissionDao extends BaseDao<SysPermission> {


    List<SysPermission> queryAllSysPermission(SysPermission record);

    List<SysPermission> querySysPermissionsByPage(Map<String, Object> parameter);

    SysPermission querySysPermission(SysPermission record);

    //根据用户id查询菜单
    List<SysPermission> findMenuListByUserId(Integer userId);

    //根据用户id查询权限URL
    List<SysPermission> findPermissionListByUserId(Integer userId);

}
