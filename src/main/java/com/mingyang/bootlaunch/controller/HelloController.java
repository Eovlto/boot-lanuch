package com.mingyang.bootlaunch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ymy
 * @program: boot-launch
 * @description: 第一个测试controller
 * @date: 2022/6/3 17:04
 * @version: 1.0
 */
@Api(tags = "测试接口")
@RestController
public class HelloController {

    /**
     * hello
     * @param name
     * @return
     */
    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(String name) {
        return "hello haha :"+ name;
    }
}
