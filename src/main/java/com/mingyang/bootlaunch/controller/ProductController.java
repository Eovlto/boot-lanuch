package com.mingyang.bootlaunch.controller;

import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.service.ProductService;
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
 * @program: boot-lanuch
 * @description: 商品
 * @date: 2022/9/12 13:37
 * @version: 1.0
 */
@Api(tags="商品")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /** 查询所有商品分页数据
     *
     */
    @ApiOperation(value = "查询所有商品数据",notes = "查询所有商品数据")
    @GetMapping("/list")
    public Result list(){
        Map<String, Object> map = new HashMap<>();
        map.put("productList",productService.list());
        return Result.success(map);
    }
}
