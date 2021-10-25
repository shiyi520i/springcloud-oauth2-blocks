package com.shiyi.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务
 *
 * @author ：ShiYI
 * @date ：Created in 2021/10/15
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private ClientDetailsService clientDetailsService;


    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String SIGNING_KEY = "uaa123";

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;


    /**
     * 客户端管理
     *
     * @param clients   客户端
     * @return void
     * @author ShiYi
     * @date 2021/10/15 11:36
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
/*        clients.inMemory()// 使用in‐memory存储
                .withClient("c1")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false) //加上验证回调地址
                .redirectUris("http://www.baidu.com");*/
        clients.withClientDetails(clientDetailsService);
    }

    /**
     *  客户端信息存储方式
     * @author ShiYi
     * @param dataSource   数据源
     * @return org.springframework.security.oauth2.provider.ClientDetailsService 
     * @date 2021/10/20 14:10
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);//编码方式
        return clientDetailsService;
    }

    /**
     * 令牌存储方式，以下为jwt
     *
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     * @author ShiYi
     * @date 2021/10/15 11:38
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     *  配置授权服务器的属性（端点管理，授权端点，令牌认证端点等）
     * @author ShiYi
     * @param endpoints   端点
     * @return void 
     * @date 2021/10/20 15:12
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints 
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices) //授权码服务
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true); service.setTokenStore(tokenStore);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    @Bean public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称秘钥，资源服务器使用该秘钥来验证
        return converter;
    }

    /**
     *  设置授权码获得方式
     * @author ShiYi
     * @param dataSource 数据源
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     * @date 2021/10/18 10:25
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//数据库获取
        }

    /**
     *  配置令牌端点的安全约束(端点怎么开放)
     * @author ShiYi
     * @param security    安全配置
     * @return void
     * @date 2021/10/15 11:52
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security
                .tokenKeyAccess("permitAll()")   //允许所有人申请令牌
                .checkTokenAccess("permitAll()") //允许所有人查看令牌信息
                .allowFormAuthenticationForClients(); //允许表单验证
    }
}
