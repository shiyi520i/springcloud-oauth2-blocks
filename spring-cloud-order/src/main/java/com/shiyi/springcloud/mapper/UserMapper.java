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
    List<User> getAllByUsername(@Param("username") String username);

    @ResultMap("BaseResultMap")
    @Select("<script>"
            + "select <include refid=\"Base_Column_List\"/>"
            + "from t_user"
            + "<where>"
            + "<if test=\"username != null and username != ''\">"
            + "username = #{username,jdbcType=VARCHAR}"
            + "</if>"
            + "</where>"
            + "</script>")
    List<User> getByUsername(@Param("username") String username);

}
