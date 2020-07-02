package com.pine.admin.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer sysRoleId;
    /**
     * 权限id
     */
    private Integer sysPermissionId;


    /**
     * 权限参数
     */
    private String perIds;
}
