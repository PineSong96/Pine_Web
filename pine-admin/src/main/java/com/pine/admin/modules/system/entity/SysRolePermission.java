package com.pine.admin.modules.system.entity;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
