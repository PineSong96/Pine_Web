package com.pine.admin.shiro;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pine.admin.modules.system.entity.SysPermission;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Pine
 * @Date: 2019/4/6
 * @Email:771190883@qq.com
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShiroUserInfo implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 头像
     */
    private String userIcon;
    /**
     * openid
     */
    private String openid;
    /**
     * 用户菜单
     */
    List<SysPermission> Menus;
    /**
     * 用户权限
     */
    List<SysPermission> permission;

    public ShiroUserInfo() {
    }

    public ShiroUserInfo nullMenus(){
        this.Menus =null;
        this.permission = null;
        return this;
    }

    public ShiroUserInfo(Integer userId, String userName, Integer userType, String userIcon, String openid, List<SysPermission> menus, List<SysPermission> permission) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userIcon = userIcon;
        this.openid = openid;
        Menus = menus;
        this.permission = permission;
    }
}
