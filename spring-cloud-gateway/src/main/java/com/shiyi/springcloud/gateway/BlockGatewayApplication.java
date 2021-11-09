package com.shiyi.springcloud.gateway;



import com.shiyi.springcloud.swagger.annotation.EnableBlockSwagger3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：ShiYI
 * @date ：Created in 2021/10/19
 */
@EnableBlockSwagger3
@SpringBootApplication
@EnableDiscoveryClient
public class BlockGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlockGatewayApplication.class, args);
    }
}
