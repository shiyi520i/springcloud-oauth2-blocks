package com.shiyi.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swaggerui配置类
 *
 * @author ：ShiYI
 * @date ：Created in 2021/9/23
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //分组名称，可以通过配置多个bean来达到分组效果
                .groupName("")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shiyi.springcloud.controller"))
                //.paths(PathSelectors.ant("/*")) 过滤mapping为"/"的所有路径
                .build();
    }
    /**
     *  信息类
     * @author ShiYi
     * @return springfox.documentation.service.ApiInfo
     * @date 2021/9/23 14:38
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试")
                .contact(new Contact("", "", ""))
                .version("1")
                .build();
    }
}

