package com.pine.admin.modules.system.service;


import com.pine.admin.modules.system.entity.ActiveUser;
import com.pine.admin.modules.system.entity.SysPermission;

import java.util.List;

/**
 * 认证授权服务接口
 *
 * @author Create By Pine
 * zhangsong@crmhby.com
 * @date 2018/3/27 10:42
 */
public interface SysService {

    //根据用户的身份和密码进行认证，如果认证通过，返回用户身份信息
    ActiveUser authenticat(String userCode, String password);

    //根据用户id查询权限范围内的菜单
    List<SysPermission> findMenuListByUserId(Integer userId);

    //根据用户id查询权限范围内的url
    List<SysPermission> findPermissionListByUserId(Integer userId);
}
