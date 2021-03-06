package com.pine.admin.modules.system.controller;

import com.pine.admin.modules.base.service.impl.ExcelPoiServiceImpl;

import com.pine.common.dto.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Pine
 * @Date: 2018/7/13 下午12:07
 * @Email:771190883@qq.com
 */

@RestController
@RequestMapping("/test")
@Api(value = "EXCEL导出", tags = {"EXCEL导出" })
public class ExcelExpeortController {


    @Autowired
    private ExcelPoiServiceImpl excelPoiService;

    /**
     * 导出
     */
    @ResponseBody
    @GetMapping(value = "/a")
    public Result exportCode(HttpServletRequest request, HttpServletResponse response, Integer productId) {
        String url = null;
        Assert.notNull(url, "URI is required");
        return Result.error("url");
    }

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public Result hello(HttpServletResponse response) {
//
//        CookieUtil.addCookie(response, "test", "test", -1);
//        return Result.success(true, "跨域访问成功");
//
//    }

}
