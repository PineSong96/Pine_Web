package com.pine.admin.modules.system.controller;

import com.pine.admin.modules.base.service.StmpMailService;
import com.pine.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Pine
 * @Date: 2018/6/26 下午5:40
 * @Email:771190883@qq.com
 */

@RestController
@RequestMapping("system/token")
public class TokenController {
    @Autowired
    private StmpMailService stmpMailService;

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public Result check(@RequestParam(value = "token", required = true) String token, @RequestParam(value = "userId", required = true) Integer userId) {

        Object cToken = null;

        String c = (String) cToken;

        if (c == null) {
            return Result.success(false, "非法用户");
        }

        if (c.equals(token)) {
            return Result.success(true, "合法用户");
        }
        return Result.success(false, "非法用户");
    }

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail(@RequestParam(value = "email", required = true) String email) {

        stmpMailService.send("771190883@qq.com", "111", "测试邮箱");

        return Result.success(true, "发送成功");
    }
}
