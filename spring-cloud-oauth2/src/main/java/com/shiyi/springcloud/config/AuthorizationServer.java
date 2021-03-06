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
 * ????????????
 *
 * @author ???ShiYI
 * @date ???Created in 2021/10/15
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
     * ???????????????
     *
     * @param clients   ?????????
     * @return void
     * @author ShiYi
     * @date 2021/10/15 11:36
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
/*        clients.inMemory()// ??????in???memory??????
                .withClient("c1")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")// ???client????????????????????? authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// ?????????????????????
                .autoApprove(false) //????????????????????????
                .redirectUris("http://www.baidu.com");*/
        clients.withClientDetails(clientDetailsService);
    }

    /**
     *  ???????????????????????????
     * @author ShiYi
     * @param dataSource   ?????????
     * @return org.springframework.security.oauth2.provider.ClientDetailsService 
     * @date 2021/10/20 14:10
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);//????????????
        return clientDetailsService;
    }

    /**
     * ??????????????????????????????jwt
     *
     * @return org.springframework.security.oauth2.provider.token.TokenStore
     * @author ShiYi
     * @date 2021/10/15 11:38
     */
    @Bean
    public TokenStore tokenStore() {
        //??????redis
        /*RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;*/
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     *  ???????????????????????????????????????????????????????????????????????????????????????
     * @author ShiYi
     * @param endpoints   ??????
     * @return void 
     * @date 2021/10/20 15:12
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints 
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices) //???????????????
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        //????????????refresh_token
        service.setSupportRefreshToken(true);
        //??????refresh_token
        //service.setReuseRefreshToken(true);
        service.setTokenStore(tokenStore);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(12 * 60 * 60); // ?????????????????????12??????
        service.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60); // ???????????????????????????7???
        return service;
    }

    @Bean public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //??????????????????????????????????????????????????????
        return converter;
    }

    /**
     *  ???????????????????????????
     * @author ShiYi
     * @param dataSource ?????????
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     * @date 2021/10/18 10:25
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//???????????????
        }

    /**
     *  ?????????????????????????????????(??????????????????)
     * @author ShiYi
     * @param security    ????????????
     * @return void
     * @date 2021/10/15 11:52
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security
                .tokenKeyAccess("permitAll()")   //???????????????????????????
                .checkTokenAccess("permitAll()") //?????????????????????????????????
                .allowFormAuthenticationForClients(); //??????????????????
    }
}
