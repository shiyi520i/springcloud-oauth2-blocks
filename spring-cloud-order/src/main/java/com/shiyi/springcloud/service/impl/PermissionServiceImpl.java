package com.shiyi.springcloud.service.impl;

import com.shiyi.springcloud.mapper.UserMapper;
import com.shiyi.springcloud.pojo.Permission;
import com.shiyi.springcloud.mapper.PermissionMapper;
import com.shiyi.springcloud.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiYi
 * @since 2021-10-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<String> getAll(String id){
        List<Permission> list = permissionMapper.getAll(id);
        List<String> permissions = new ArrayList<>();
        //获取授权码信息
        list.iterator().forEachRemaining(c->permissions.add(c.getCode()));
        return permissions;
    }

}
