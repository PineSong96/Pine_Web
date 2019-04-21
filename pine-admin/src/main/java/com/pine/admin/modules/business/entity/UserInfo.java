package com.pine.admin.modules.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel(value = "用户信息对象", description = "用户信息对象")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    private Integer id;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "type", hidden = true)
    private Integer type;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "status", hidden = true)
    private String status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "backupA", hidden = true)
    private String backupA;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "backupB", hidden = true)
    private String backupB;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "backupC", hidden = true)
    private String backupC;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "serialNumber", hidden = true)
    private String backupD;
    /**
     *
     */
    @ApiModelProperty(value = "", name = "backupE", hidden = true)
    private String backupE;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号", name = "userPhone")
    private String userPhone;
    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", name = "password")
    private String password;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", name = "idCard")
    private String idCard;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", name = "userIcon")
    private String userIcon;
    /**
     * 微信OPENID
     */
    @ApiModelProperty(value = "微信OPENID", name = "wxOpenid")
    private String wxOpenid;

}
