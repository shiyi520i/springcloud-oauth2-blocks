package com.shiyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author ：ShiYI
 * @date ：Created in 2021/9/30
 */
@EnableOpenApi
@SpringBootApplication
public class OApplication {
    public static void main(String[] args) {
        SpringApplication.run(OApplication.class,args);
    }
}
