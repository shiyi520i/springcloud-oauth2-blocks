package com.shiyi.springcloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：ShiYI
 * @date ：Created in 2021/10/14
 */
@RestController
class EchoController {
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable(value = "string") String string) {
        return "Hello Nacos Discovery " + string;
    }
}
