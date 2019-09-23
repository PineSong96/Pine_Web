package com.pine.admin.modules.system.service.impl;

import com.pine.admin.modules.system.dao.SysPermissionDao;
import com.pine.admin.modules.system.dao.SysUserDao;
import com.pine.admin.modules.system.entity.ActiveUser;
import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.entity.SysUser;
import com.pine.admin.modules.system.service.SysService;
import com.pine.common.utils.Constant;
import com.pine.common.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Create By Pine
 * zhangsong@crmhby.com
 * @date 2018/3/27 10:44
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysUserDao sysUserMapper;

    @Autowired
    private SysPermissionDao sysPermissionMapper;

    @Override
    public ActiveUser authenticat(String userCode, String password) {

        /**
         * 认证过程： 根据用户身份(账号)查询数据库，如果查询不到则用户不存在
         * 对输入的密码和数据库密码进行比对，如果一致则认证通过
         */
        // 根据用户账号查询数据库
        SysUser sysUser = new SysUser();

        sysUser.setUsercode(userCode);

        sysUser = sysUserMapper.querySysUser(sysUser);


        // 数据库密码（MD5加密后的密码）
        String password_db = sysUser.getPassword();

        // 对输入的密码和数据库密码进行比对，如果一致，认证通过
        // 对页面输入的密码进行MD5加密
        String password_input_md5 = MD5Util.getMD5(password);

        //得到用户id
        Integer userId = sysUser.getId();
        //根据用户id查询菜单
        List<SysPermission> menus = this.findMenuListByUserId(userId);
        //根据用户id查询权限url
        List<SysPermission> permissions = this.findPermissionListByUserId(userId);

        //认证通过，返回用户身份信息
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(userId);
        activeUser.setUserCode(userCode);
        activeUser.setUserName(sysUser.getUsername());

        //放入权限范围的菜单和url
        activeUser.setMenus(menus);
        activeUser.setPermissions(permissions);

        return activeUser;
    }

    @Override
    public List<SysPermission> findMenuListByUserId(Integer userId) {
        return sysPermissionMapper.findMenuListByUserId(userId, Constant.PER_ID);
    }

    @Override
    public List<SysPermission> findPermissionListByUserId(Integer userId) {
        return sysPermissionMapper.findPermissionListByUserId(userId);
    }

}
