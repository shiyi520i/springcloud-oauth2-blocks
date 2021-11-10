package com.shiyi.springcloud.mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.ResultMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shiyi.springcloud.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ShiYi
 * @since 2021-10-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getByUsername(@Param("username") String username);


}
