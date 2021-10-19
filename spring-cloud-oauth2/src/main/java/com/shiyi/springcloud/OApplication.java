package com.shiyi.springcloud;

import com.shiyi.springcloud.swagger.annotation.EnableBlockSwagger3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author ：ShiYI
 * @date ：Created in 2021/9/30
 */
@EnableBlockSwagger3
@SpringBootApplication
public class OApplication {
    public static void main(String[] args) {
        SpringApplication.run(OApplication.class,args);
    }
}
