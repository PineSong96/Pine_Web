package com.pine.admin.modules.base.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 区县信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:56
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "区县信息表对象", description = "区县信息表对象")
public class Areas implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private Integer id;
    /**
     * 区县编码
     */
    @ApiModelProperty(value = "区县编码", name = "areaid")
    private String areaid;
    /**
     * 区县名称
     */
    @ApiModelProperty(value = "区县名称", name = "area")
    private String area;
    /**
     * 所属城市编码
     */
    @ApiModelProperty(value = "所属城市编码", name = "cityid")
    private String cityid;

}
