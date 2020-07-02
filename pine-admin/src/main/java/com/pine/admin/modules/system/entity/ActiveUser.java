package com.pine.admin.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户身份信息，存入Session  由于Tomcat正常关闭时会将Session序列化的本地硬盘上，所以实现Serializable接口
 *
 * @author Create By Pine
 * zhangsong@crmhby.com
 * @date 2018/3/27 10:40
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * //用户id(主键)
     */
    private Integer userId;
    /**
     * // 用户账号
     */
    private String userCode;
    /**
     * // 用户姓名
     */
    private String userName;

    private String uidToken;

    /**
     *  //菜单
     */
    private List<SysPermission> menus;
    /**
     *  //权限
     */
    private List<SysPermission> permissions;
}
