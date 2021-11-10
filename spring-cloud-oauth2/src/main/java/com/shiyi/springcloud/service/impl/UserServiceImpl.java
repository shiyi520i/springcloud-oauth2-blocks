package com.shiyi.springcloud.service.impl;


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

        com.shiyi.springcloud.pojo.User user = userService.getAllByUsername(s);
        List<String> permissions = permissionService.getAll(user.getId().toString());
        String[] perarray = new String[permissions.size()];
        permissions.toArray(perarray);

            UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(perarray).build();
            return userDetails;
        }
    }

