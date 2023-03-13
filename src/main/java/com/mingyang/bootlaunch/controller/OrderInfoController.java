package com.mingyang.bootlaunch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.entity.OrderInfo;
import com.mingyang.bootlaunch.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ymy
 * @program: boot -lanuch
 * @description:
 * @date: 2022 /9/12 21:57
 * @version: 1.0
 */
@Api(tags="订单管理")
@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation("获取订单列表")
    @GetMapping("list")
    public Result list() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(OrderInfo::getCreateTime);
        Map<String, Object> map = new HashMap<>();
        map.put("list",orderInfoService.list(queryWrapper));
        return Result.success(map);
    }

}
