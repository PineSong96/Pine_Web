package com.pine.admin.shiro;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pine.admin.modules.system.entity.SysPermission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Pine
 * @Date: 2019/4/6
 * @Email:771190883@qq.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
