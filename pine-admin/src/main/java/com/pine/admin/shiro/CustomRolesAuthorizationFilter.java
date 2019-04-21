package com.pine.admin.shiro;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.HttpMethod;
import com.pine.common.dto.Result;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: Pine
 * @Date: 2019/4/19
 * @Email:771190883@qq.com
 */
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) {
//        Subject subject = getSubject(req, resp);
//        String[] rolesArray = (String[]) mappedValue;
//        //如果没有角色限制，直接放行
//        if (rolesArray == null || rolesArray.length == 0) {
//            return true;
//        }
//        for (int i = 0; i < rolesArray.length; i++) {
//            //若当前用户是rolesArray中的任何一个，则有权限访问
//            if (subject.hasRole(rolesArray[i])) {
//                return true;
//            }
//        }

        return true;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        //处理跨域问题，跨域的请求首先会发一个options类型的请求
        if (servletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        boolean isAccess = isAccessAllowed(request, response, mappedValue);
        if (isAccess) {
            return true;
        }
        servletResponse.setCharacterEncoding("UTF-8");
        Subject subject = getSubject(request, response);
        PrintWriter printWriter = servletResponse.getWriter();
        servletResponse.setContentType("application/json;charset=UTF-8");
        servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Vary", "Origin");
        String respStr;
        if (subject.getPrincipal() == null) {
            respStr = JSONObject.toJSONString(Result.error("登录无效"));
        } else {
            respStr = JSONObject.toJSONString(Result.error("登录无效"));
        }
        printWriter.write(respStr);
        printWriter.flush();
        servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
        return false;
    }
}
