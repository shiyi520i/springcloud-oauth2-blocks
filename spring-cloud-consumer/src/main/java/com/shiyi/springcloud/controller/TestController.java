package com.shiyi.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

/**
 * @author ：ShiYI
 * @date ：Created in 2021/10/14
 */
@RestController
public class TestController {
    @Autowired
    private  RestOperations restTemplate;
    /*@Autowired
    private  RestTemplate restTemplate;*/

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://spring-cloud-order/echo/" + str, String.class);
    }
}
