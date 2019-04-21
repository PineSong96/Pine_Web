/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.pine.admin.shiro;

import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.system.entity.SysUser;
import com.pine.common.exception.ApiException;
import com.pine.common.utils.Constant;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Shiro工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ShiroUtils {
    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";
    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static ShiroUserInfo getShiroUserInfo() {
        return (ShiroUserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return getShiroUserInfo().getUserId();
    }

    public static Integer getWeiXinUserId() {
        ShiroUserInfo shiroUserInfo = getShiroUserInfo();
        if (shiroUserInfo.getUserType().equals(Constant.USER_WEIXIN)) {
            return shiroUserInfo.getUserId();
        }
        throw new ApiException("非微信用户登录");
    }

    public static String getWeiXinOpenId() {
        ShiroUserInfo shiroUserInfo = getShiroUserInfo();
        if (shiroUserInfo.getUserType().equals(Constant.USER_WEIXIN)) {
            return shiroUserInfo.getOpenid();
        }
        throw new ApiException("非微信用户登录");
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }


    public static String getKaptcha(String key) throws ApiException {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new ApiException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
