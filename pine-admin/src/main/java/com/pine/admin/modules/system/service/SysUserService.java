package com.pine.admin.modules.system.service;

import com.pine.admin.modules.system.entity.SysUser;
import com.pine.common.dto.Page;

import java.util.List;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
public interface SysUserService {

    List<SysUser> getSysUserList(SysUser record);

    List<SysUser> getSysUserByPage(Page page, SysUser record);

    SysUser getSysUserById(String id);

    SysUser getSysUserByUserCode(String UserCode);


    SysUser getSysUser(SysUser record);

    boolean createSysUser(SysUser record);

    boolean deleteSysUser(String id);

    boolean updateSysUser(SysUser record);

}

