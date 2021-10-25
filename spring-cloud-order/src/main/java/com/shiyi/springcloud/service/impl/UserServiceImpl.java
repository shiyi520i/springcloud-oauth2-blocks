package com.shiyi.springcloud.service.impl;

import com.shiyi.springcloud.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务类
 *
 * @author ：ShiYI
 * @date ：Created in 2021/9/22
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private IUserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //TODO
        //List<String> permissions = userService.getAll(user.getId());
        List<String> permissions = permissionService.getAll("");
        String[] perarray = new String[permissions.size()];
        permissions.toArray(perarray);

            UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm").authorities("p1").build();//perarray
            return userDetails;
        }
    }

