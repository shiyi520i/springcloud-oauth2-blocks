package com.shiyi.springcloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关全局过滤
 *
 * @author ：ShiYI
 * @date ：Created in 2021/10/19
 */
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    //具体的验证可以获取相应的数据进行验证

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /*String name = exchange.getRequest().getQueryParams().getFirst("name");//获取请求的参数，进行验证
        if (name==null){
            log.info("请求的参数为空，数据异常");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);  //放行给下一个过滤器
    }

    @Override
    public int getOrder() {
        return 0;  //执行的顺序，数值越小是，优先级越高
    }
}
