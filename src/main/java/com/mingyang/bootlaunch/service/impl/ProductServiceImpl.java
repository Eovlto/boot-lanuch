package com.mingyang.bootlaunch.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mingyang.bootlaunch.entity.Product;
import com.mingyang.bootlaunch.mapper.ProductMapper;
import com.mingyang.bootlaunch.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
