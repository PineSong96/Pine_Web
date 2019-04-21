package com.pine.admin.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.system.entity.SysUser;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysUserDao extends BaseDao<SysUser> {

    List<SysUser> queryAllSysUser(SysUser record);

    List<SysUser> querySysUsersByPage(Map<String, Object> parameter);

    SysUser querySysUser(SysUser record);

    SysUser selectByUserCode(String userCode);
}
