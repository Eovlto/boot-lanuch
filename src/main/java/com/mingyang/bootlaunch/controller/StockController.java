package com.mingyang.bootlaunch.controller;


import com.mingyang.bootlaunch.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: ymy
 * @program: lockTest
 * @description:
 * @date: 2022/11/1 16:49
 * @version: 1.0
 */

@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @GetMapping("/deduct")
    public String deduct(){
        stockService.deduct();
        return "执行库存操作";
    }
}
