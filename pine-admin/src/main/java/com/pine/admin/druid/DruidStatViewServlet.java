package com.pine.admin.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * druid数据源状态监控.
 * 基本上没怎么用过
 *
 * @Author: Pine
 * @Date: 2018/5/24 下午2:13
 * @Email:771190883@qq.com
 */

@WebServlet(urlPatterns = "/druid/*",

        initParams = {

//                @WebInitParam(name="allow",allowvalue="192.168.1.72,127.0.0.1"),// IP白名单(没有配置或者为空，则允许所有访问)

//                @WebInitParam(name="deny",value="192.168.1.73"),// IP黑名单 (存在共同时，deny优先于allow)

                @WebInitParam(name = "loginUsername", value = "admin"),// 用户名

                @WebInitParam(name = "loginPassword", value = "123456"),// 密码

                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能

        }

)

public class DruidStatViewServlet extends StatViewServlet {
}
