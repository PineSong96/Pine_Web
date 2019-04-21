package com.pine.admin.shiro;

import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.business.service.UserInfoService;
import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.entity.SysUser;
import com.pine.admin.modules.system.service.SysService;
import com.pine.admin.modules.system.service.SysUserService;
import com.pine.common.utils.Constant;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private SysUserService userService;
    @Autowired
    @Lazy
    private SysService sysService;
    @Autowired
    @Lazy
    private UserInfoService userInfoService;

    /**
     * 添加用户权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        Integer userId = ShiroUtils.getUserId();
        //系统管理员，拥有最高权限
        List<SysPermission> permissions = sysService.findPermissionListByUserId(userId);
        List<String> permsList = permissions.stream().map(SysPermission::getPercode).collect(Collectors.toList());
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);

        return info;
    }

    /**
     * 验证用户名密码
     *
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        //若为微信用户token
        if (token instanceof WxOpenIdToken) {
            String openid = (String) token.getPrincipal();
            //sysUserDao.getbyWxaOpenId 根据openid查询是否有已绑定的userid,有就时已绑定
            UserInfo exUser = userInfoService.getUserInfoByOpenId(openid);
            if (exUser == null) {
                throw new UnknownAccountException("账号不存在");
//            return null;
            }
            ShiroUserInfo shiroUserInfo = new ShiroUserInfo(
                    exUser.getId(),
                    exUser.getUserName(),
                    Constant.USER_WEIXIN,
                    exUser.getUserIcon(), exUser.getWxOpenid(), null, null);
            /**
             * 设置权限
             */
            String password = ShiroUtils.sha256(Constant.USER_PASSWORD, Constant.USER_SALT);
            info = new SimpleAuthenticationInfo(shiroUserInfo, password, ByteSource.Util.bytes(Constant.USER_SALT), getName());
            return info;
        } else {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            //查询用户信息
            SysUser user = userService.getSysUserByUserCode(usernamePasswordToken.getUsername());
            //账号不存在
            if (user == null) {
                throw new UnknownAccountException("账号或密码不正确");
//            return null;
            }

            //单用户登录
            //处理session
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
            //获取当前已登录的用户session列表
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            ShiroUserInfo temp;
            for(Session session : sessions){
                //清除该用户以前登录时保存的session，强制退出
                Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                if (attribute == null) {
                    continue;
                }

                temp = (ShiroUserInfo) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if(usernamePasswordToken.getUsername().equals(temp.getUserName())) {
                    sessionManager.getSessionDAO().delete(session);
                }
            }

            //账号锁定
            if ("0".equals(user.getLocked())) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }

            ShiroUserInfo shiroUserInfo = new ShiroUserInfo(
                    user.getId(),
                    user.getUsername(),
                    Constant.USER_ADMIN, null,
                    null, null, null);
            /**
             * 设置权限
             */
            shiroUserInfo.setPermission(sysService.findPermissionListByUserId(shiroUserInfo.getUserId()));
            shiroUserInfo.setMenus(sysService.findMenuListByUserId(shiroUserInfo.getUserId()));

            info = new SimpleAuthenticationInfo(shiroUserInfo, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

        }
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}
