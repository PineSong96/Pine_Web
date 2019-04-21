package com.pine.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Pine
 * @Date: 2018/5/28 下午7:03
 * @Email:771190883@qq.com
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    InterceptorConfig sessioninterceptor() {
        return new InterceptorConfig();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new InterceptorConfig());
        super.addInterceptors(registry);
    }
}
