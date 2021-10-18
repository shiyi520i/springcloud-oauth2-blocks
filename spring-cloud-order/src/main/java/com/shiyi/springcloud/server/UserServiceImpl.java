package com.shiyi.springcloud.server;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 *
 * @author ：ShiYI
 * @date ：Created in 2021/9/22
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

            UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm").authorities("p1").build();
            return userDetails;
        }
    }

