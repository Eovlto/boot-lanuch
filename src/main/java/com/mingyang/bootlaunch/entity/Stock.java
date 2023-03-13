package com.mingyang.bootlaunch.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: ymy
 * @program: lockTest
 * @description:
 * @date: 2022/11/1 16:45
 * @version: 1.0
 */
@Data
@TableName("stock")
public class Stock {
    private Integer id;
    private Integer stock;
}
