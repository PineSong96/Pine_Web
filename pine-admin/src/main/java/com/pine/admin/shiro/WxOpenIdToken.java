package com.pine.admin.shiro;

import com.pine.common.utils.Constant;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author: Pine
 * @Date: 2019/4/1
 * @Email:771190883@qq.com
 */
public class WxOpenIdToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -2564928913725078138L;

    public WxOpenIdToken() {
        super();
    }

    /**
     * 免密登录
     */
    public WxOpenIdToken(String username) {
        super(username, Constant.USER_PASSWORD, true, null);
    }

    /**
     * 账号密码登录
     */
    public WxOpenIdToken(String username, String password) {
        super(username, password, true, null);
    }

}
