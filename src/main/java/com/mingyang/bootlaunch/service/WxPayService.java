package com.mingyang.bootlaunch.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/9/12 18:16
 * @version: 1.0
 */
public interface WxPayService {

    /**
     * 二维码统一下单支付
     * @param productId
     * @return
     */
    Map<String, Object> nativePay(Long productId) throws IOException;

    void processOrder(Map<String, Object> bodyJson) throws GeneralSecurityException;
}
