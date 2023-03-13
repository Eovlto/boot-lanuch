package com.mingyang.bootlaunch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mingyang.bootlaunch.entity.Stock;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/11/1 19:05
 * @version: 1.0
 */
public interface StockService extends IService<Stock> {
    void deduct();
}
