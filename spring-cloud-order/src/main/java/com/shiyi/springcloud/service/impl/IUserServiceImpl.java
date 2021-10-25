package com.shiyi.springcloud.service.impl;


import com.shiyi.springcloud.pojo.User;
import com.shiyi.springcloud.mapper.UserMapper;
import com.shiyi.springcloud.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
