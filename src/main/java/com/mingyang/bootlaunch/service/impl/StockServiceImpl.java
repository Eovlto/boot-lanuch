package com.mingyang.bootlaunch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mingyang.bootlaunch.entity.Stock;
import com.mingyang.bootlaunch.mapper.StockMapper;
import com.mingyang.bootlaunch.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ymy
 * @program: lockTest
 * @description:
 * @date: 2022/11/1 16:46
 * @version: 1.0
 */
@Slf4j
@Service
public class StockServiceImpl  extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Resource
    private StockMapper stockMapper;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void deduct() {
        lock.lock();
        try {
            Stock stock = stockMapper.selectById(1);
            if((stock.getStock() - 1) >0) {
                stock.setStock(stock.getStock() - 1);
                log.info("剩余库存：{}",stock.getStock());
                stockMapper.updateById(stock);
            }else {
                log.error("库存不足");
            }
        } finally {
            lock.unlock();
        }
    }
}
