package com.shiyi.springcloud.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 网页控制类
 *
 * @author ：ShiYI
 * @date ：Created in 2021/9/17
 */
@Api("nimei")
@Controller
public class WebController {
    @GetMapping("/")
    public String test(){
        return "hello";
    }

}
