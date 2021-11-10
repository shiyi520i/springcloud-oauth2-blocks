package com.shiyi.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ShiYi
 * @since 2021-10-19
 */
@Getter
@Setter
@TableName("t_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("权限标识符")
    @TableField("`code`")
    private String code;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("请求地址")
    @TableField("url")
    private String url;


}
