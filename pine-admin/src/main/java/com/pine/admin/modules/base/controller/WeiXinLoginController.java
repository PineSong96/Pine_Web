package com.pine.admin.modules.base.controller;

import com.pine.admin.modules.base.service.WeiXinService;
import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.business.service.UserInfoService;
import com.pine.admin.shiro.ShiroUtils;
import com.pine.admin.shiro.WxOpenIdToken;
import com.pine.common.dto.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Pine
 * @Date: 2019/3/31
 * @Email:771190883@qq.com
 */
@RestController
@RequestMapping("wx")
public class WeiXinLoginController {

    @Autowired
    private WeiXinService weiXinService;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public Result userLoginWx(@RequestParam(value = "code", required = true) String code) {
        boolean b = weiXinService.weixinLogin(code);
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        if (b) {
            return Result.success(true, ShiroUtils.getShiroUserInfo());
        }
        return Result.error("error");
    }

    @PostMapping("/logintest")
    public Result logintest() {
        try {
            UserInfo useinfo = userInfoService.getById("1");
            Subject subject = SecurityUtils.getSubject();
            WxOpenIdToken wxOpenIdToken = new WxOpenIdToken(useinfo.getWxOpenid());
            subject.login(wxOpenIdToken);
            SecurityUtils.getSubject().getSession().setTimeout(-1000L);
            return Result.success(true, ShiroUtils.getShiroUserInfo());
        } catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return Result.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return Result.error("账户验证失败");
        }
    }


}
