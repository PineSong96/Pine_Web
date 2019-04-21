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
 * 省份信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
@Getter
@Setter
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel(value = "省份信息对象", description = "省份信息对象")
public class Provinces implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "id")
    private Integer id;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "provinceid")
    private Integer provinceid;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "province")
    private String province;

    private List<Cities> cities;
}
