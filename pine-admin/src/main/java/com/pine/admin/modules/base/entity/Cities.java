package com.pine.admin.modules.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 城市信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
@Getter
@Setter
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel(value = "城市信息表对象", description = "城市信息表对象")
public class Cities implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private Integer id;
    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码", name = "cityid")
    private String cityid;
    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称", name = "city")
    private String city;
    /**
     * 所属省份编码
     */
    @ApiModelProperty(value = "所属省份编码", name = "provinceid")
    private String provinceid;

    private List<Areas> areas;
}
