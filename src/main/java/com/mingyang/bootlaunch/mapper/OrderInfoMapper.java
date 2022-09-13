package com.mingyang.bootlaunch.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mingyang.bootlaunch.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    Boolean saveCodeUrl(@Param("orderNo") String orderNo, @Param("codeUrl") String codeUrl);
}
