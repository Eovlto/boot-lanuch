package com.mingyang.bootlaunch.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mingyang.bootlaunch.common.utils.OrderNoUtils;
import com.mingyang.bootlaunch.entity.OrderInfo;
import com.mingyang.bootlaunch.entity.Product;
import com.mingyang.bootlaunch.enums.OrderStatus;
import com.mingyang.bootlaunch.mapper.OrderInfoMapper;
import com.mingyang.bootlaunch.service.OrderInfoService;
import com.mingyang.bootlaunch.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private ProductService productService;

    @Override
    public Boolean saveCodeUrl(String orderNo, String codeUrl)
    {
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .eq(OrderInfo::getOrderNo,orderNo)
                .set(OrderInfo::getCodeUrl,codeUrl);
        return update(wrapper);
    }

    @Override
    public OrderInfo createOrderByProductId(Long productId)
    {
        // 查找已存在但是未支付的订单
        OrderInfo orderInfo = this.getNoPayOrderByProductId(productId);
        if(orderInfo != null) {
            return orderInfo;
        }

        Product product = productService.getById(productId);

        orderInfo = new OrderInfo();
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());
        orderInfo.setProductId(productId);
        orderInfo.setTotalFee(product.getPrice());
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());
        this.save(orderInfo);
        return orderInfo;
    }

    /**
     * 根据订单号更新订单状态
     * @param orderNo
     * @param orderStatus
     */
    @Override
    public void updateStatusByOrderNo(String orderNo, String orderStatus) {
        log.info("更新订单状态 ====>>> {}",orderStatus);
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .eq(OrderInfo::getOrderNo,orderNo)
                .set(OrderInfo::getOrderStatus,orderStatus);
        this.update(wrapper);
    }

    @Override
    public String getOrderStatus(String orderNo) {
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OrderInfo::getOrderNo,orderNo);
        OrderInfo orderInfo = baseMapper.selectOne(wrapper);
        if (orderInfo == null) {
            return null;
        }
        return orderInfo.getOrderStatus();
    }

    private OrderInfo getNoPayOrderByProductId(Long productId) {
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(OrderInfo::getProductId,productId)
                .eq(OrderInfo::getOrderStatus,OrderStatus.NOTPAY.getType());
        return this.getOne(wrapper);
    }
}
