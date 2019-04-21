package com.pine.admin.modules.base.service;

/**
 * @Author: Pine
 * @Date: 2019/3/31
 * @Email:771190883@qq.com
 */
public interface WeiXinService {
    /**
     * 微信登陆接口
     *
     * @param code
     * @return
     */
    boolean weixinLogin(String code);

    /**
     * 微信回调接口
     *
     * @param notifyData
     * @return
     */
    String payBack(String notifyData);
}
