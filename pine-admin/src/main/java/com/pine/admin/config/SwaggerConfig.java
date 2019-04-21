package com.pine.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Pine
 * @Date: 2018/5/23 下午4:16
 * @Email:771190883@qq.com
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 创建api的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("集成Swagger2构建RESTful APIs").description("高性能在线测试接口文档自动生成开发平台").termsOfServiceUrl("https://www.crmhby.com").contact(" Pine Sir")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pine.admin"))//这是注意的代码
                .paths(PathSelectors.any())
                .build();
    }


}
