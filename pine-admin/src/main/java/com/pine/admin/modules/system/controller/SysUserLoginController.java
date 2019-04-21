package com.pine.admin.modules.system.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.entity.SysUserRole;
import com.pine.admin.modules.system.service.SysUserRoleService;
import com.pine.admin.shiro.ShiroUtils;
import com.pine.common.dto.Result;
import com.pine.common.utils.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Api(value = "A管理员登陆", tags = {"A管理员登陆" })
@RestController
@Slf4j
public class SysUserLoginController {

    @Autowired
    private Producer producer;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @PostMapping("login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户名", example = "root", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", example = "1234", required = true, dataType = "string", paramType = "query")
//            @ApiImplicitParam(name = "rand", value = "验证码", example = "a",required = true, dataType = "string", paramType = "query")
    })

    public Result login(@RequestParam(required = true, value = "userCode") String userCode, @RequestParam(required = true, value = "password") String password, HttpServletResponse response
    ) {

//        if (!"a".equals(rand)){
//            String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//
//            if(!rand.equalsIgnoreCase(kaptcha)){
//                return Result.error("验证码不正确");
//            }
//        }
        String role = "0";
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userCode, password);
            subject.login(token);
            SysUserRole sysUserRole = sysUserRoleService.selectByUser(ShiroUtils.getUserId());
            if (sysUserRole != null){
                role = "1";
            }
        } catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return Result.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return Result.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return Result.error("账户验证失败");
        }
        return Result.success(true, role);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Result logout() {
        String username = ShiroUtils.getShiroUserInfo().getUserName();
        ShiroUtils.logout();
        return Result.success(true, "用户:" + username + "退出登录成功");
    }

    /**
     * error
     */
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public Result error() {
        return Result.error( "系统出错");
    }
}
