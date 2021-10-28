package com.shiyi.springcloud.security.config;

import com.shiyi.springcloud.security.support.ResouceSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ：ShiYI
 * @date ：Created in 2021/10/15
 */
//@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final ResouceSecurityProperties resouceSecurityProperties;
//
//    /**
//     *  安全拦截机制
//     * @author ShiYi
//     * @param http
//     * @return void
//     * @date 2021/10/28 15:59
//     */
//    @Override
//     protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
//                .anyRequest().permitAll();
//    }
//}
