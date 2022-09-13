package com.mingyang.bootlaunch.common.config;

import cn.hutool.core.io.resource.ResourceUtil;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;


@Configuration
@PropertySource("classpath:wxpay.properties") //读取配置文件
@ConfigurationProperties(prefix="wxpay") //读取wxpay节点
@Data //使用set方法将wxpay节点中的值填充到当前类的属性中
public class WxPayConfig {

    // 商户号
    private String mchId;

    // 商户API证书序列号
    private String mchSerialNo;

    // 商户私钥文件
    private String privateKeyPath;

    // APIv3密钥
    private String apiV3Key;

    // APPID
    private String appid;

    // 微信服务器地址
    private String domain;

    // 接收结果通知地址
    private String notifyDomain;


    /**
     * 获取商户私钥
     * @param fileName
     * @return
     */
    public PrivateKey getPrivateKey(String fileName) {
        try {
            return PemUtil.loadPrivateKey(
                    new FileInputStream(ResourceUtils.getFile(fileName))
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException("私钥文件不存在",e);
        }
    }

    /**
     * 获取签名验证器
     * @return
     */
    @Bean
    public ScheduledUpdateCertificatesVerifier getVerifier(){
        // 获取商户西药
        PrivateKey key = getPrivateKey(privateKeyPath);
        // 私钥签名对象
        PrivateKeySigner keySigner = new PrivateKeySigner(mchSerialNo, key);
        // 身份认证对象
        WechatPay2Credentials credentials = new WechatPay2Credentials(mchId, keySigner);
        return new ScheduledUpdateCertificatesVerifier(credentials,
                apiV3Key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取http请求响应对象
     * @param verifier
     * @return
     */
    @Bean
    public CloseableHttpClient getWxPayClient(ScheduledUpdateCertificatesVerifier verifier){
        PrivateKey privateKey = getPrivateKey(privateKeyPath);
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId,mchSerialNo,privateKey)
                .withValidator(new WechatPay2Validator(verifier));
        return builder.build();
    }
}
