package com.mingyang.bootlaunch.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.mingyang.bootlaunch.entity.PaymentInfo;
import com.mingyang.bootlaunch.enums.PayType;
import com.mingyang.bootlaunch.mapper.PaymentInfoMapper;
import com.mingyang.bootlaunch.service.PaymentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Override
    public void createPaymentInfo(String plainText) {
        log.info("记录支付日志..");
        Gson gson = new Gson();
        HashMap plainMap = gson.fromJson(plainText, HashMap.class);
        // 订单号
        String orderNo = (String) plainMap.get("out_trade_no");
        // 业务编号
        String transactionId = (String) plainMap.get("transaction_id");
        // 支付类型
        String tradeType = (String) plainMap.get("trade_type");
        // 交易状态
        String tradeState = (String) plainMap.get("trade_state");
        // 用户支付金额
        Map<String, Object> amount = (Map) plainMap.get("amount");
        Integer payerTotal = ((Double) amount.get("payer_total")).intValue();

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setPaymentType(PayType.WXPAY.getType());
        paymentInfo.setTransactionId(transactionId);
        paymentInfo.setPaymentType(tradeType);
        paymentInfo.setTradeState(tradeState);
        paymentInfo.setPayerTotal(payerTotal);
        paymentInfo.setContent(plainText);
        this.save(paymentInfo);
    }
}
