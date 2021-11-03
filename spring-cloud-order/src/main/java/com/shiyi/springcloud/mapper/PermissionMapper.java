package com.shiyi.springcloud.mapper;

import com.shiyi.springcloud.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShiYi
 * @since 2021-10-19
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {



    @Select("SELECT * FROM t_permission WHERE id IN(\n" +
            "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
            "SELECT role_id FROM t_user_role WHERE user_id = #{id} \n" + ")\n" + ")")
    List<Permission> getAll(String id);



}
