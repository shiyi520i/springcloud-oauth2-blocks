package com.shiyi.springcloud.generator;

import com.shiyi.springcloud.mybatis.generator.MyGenerator;

/**
 * 自动生成mybatis
 *
 * @author ：ShiYI
 * @date ：Created in 2021/10/19
 */
public class AutoMybatis {
    public static void main(String[] args) {
        MyGenerator.auto("com.shiyi.springcloud","spring-cloud-order","t_permission");
    }
}
