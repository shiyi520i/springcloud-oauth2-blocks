package com.shiyi.springcloud.service.impl;


import com.shiyi.springcloud.pojo.User;
import com.shiyi.springcloud.mapper.UserMapper;
import com.shiyi.springcloud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiYi
 * @since 2021-10-19
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User getAllByUsername(String username) {
       List<User> users = userMapper.getByUsername(username);
       return users.size()>0?users.stream().filter(user -> user != null).limit(1).collect(Collectors.toList()).get(0):null;
    }
}
