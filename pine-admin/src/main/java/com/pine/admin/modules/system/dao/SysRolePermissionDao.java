package com.pine.admin.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.entity.SysRolePermission;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysRolePermissionDao extends BaseDao<SysRolePermission> {

    List<SysRolePermission> queryAllSysRolePermission(SysRolePermission record);

    List<SysRolePermission> querySysRolePermissionsByPage(Map<String, Object> parameter);

    SysRolePermission querySysRolePermission(SysRolePermission record);

    /**
     * //根据用户id查询菜单
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermission> findMenuListByUserId(Integer userId) throws Exception;

    /**
     *  //根据用户id查询权限URL
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermission> findPermissionListByUserId(Integer userId) throws Exception;


    int delRole(Integer sysRoleId);

}
