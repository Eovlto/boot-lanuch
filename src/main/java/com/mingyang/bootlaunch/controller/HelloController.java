package com.mingyang.bootlaunch.controller;

import com.mingyang.bootlaunch.common.utils.RedisTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTool redisUtil;
    /**
     * hello
     * @param name
     * @return
     */
    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(String name) {
        redisUtil.set("name", name, TimeUnit.SECONDS.toSeconds(10));
        return "hello haha :"+ name;
    }

    @GetMapping("/getCache")
    @ApiOperation("getCache")
    public String getCache() {
        return redisUtil.get("name").toString();
    }
}
