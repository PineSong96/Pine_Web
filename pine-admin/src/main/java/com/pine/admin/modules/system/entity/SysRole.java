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
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 是否可用,1：可用，0不可用
     */
    private String available;
    /**
     *
     */
    private String delFlag;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

}
