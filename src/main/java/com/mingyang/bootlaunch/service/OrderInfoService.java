package com.mingyang.bootlaunch.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mingyang.bootlaunch.entity.OrderInfo;
import com.mingyang.bootlaunch.enums.OrderStatus;

public interface OrderInfoService extends IService<OrderInfo> {

    Boolean saveCodeUrl(String orderNo, String codeUrl);

    OrderInfo createOrderByProductId(Long productId);

    void updateStatusByOrderNo(String orderNo, String success);
}
