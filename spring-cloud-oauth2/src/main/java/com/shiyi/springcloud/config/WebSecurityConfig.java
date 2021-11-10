package com.shiyi.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security
 *
 * @author ：ShiYI
 * @date ：Created in 2021/10/15
 */
@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 密码编码方式
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author ShiYi
     * @date 2021/10/18 10:09
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 安全拦截机制
     *
     * @param http
     * @return void
     * @author ShiYi
     * @date 2021/10/18 10:10
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/r/r1")
                .hasAnyAuthority("p1")
                .antMatchers("/login*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**"
                ,"/swagger-resources/**"
                ,"/swagger-ui/**"
                ,"/v3/**"
                ,"/v2/**");
    }
}

