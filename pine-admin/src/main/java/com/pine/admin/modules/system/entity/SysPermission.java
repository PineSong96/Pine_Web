package com.pine.admin.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Pine
 * @email 771190883@qq.com
 * @date 2018-06-21 13:55:06
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型：menu,button,
     */
    private String type;
    /**
     * 访问url地址
     */
    private String url;
    /**
     * 权限代码字符串
     */
    private String perCode;
    /**
     * 父结点id
     */
    private Integer parentId;
    /**
     * 父结点id列表串
     */
    private String parentIds;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否可用,1：可用，0不可用
     */
    private String available;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    /**
     * 权限信息
     */
    private List<SysPermission> children;


}
