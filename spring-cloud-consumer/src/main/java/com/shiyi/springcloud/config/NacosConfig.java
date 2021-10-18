package com.shiyi.springcloud.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;


/**
 * @author ：ShiYI
 * @date ：Created in 2021/10/14
 */
@Configuration
public class NacosConfig {

    @Bean
    @LoadBalanced
    RestOperations restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

/*    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }*/
}
