package com.mingyang.bootlaunch.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mingyang.bootlaunch.common.config.WxPayConfig;
import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.common.utils.HttpUtils;
import com.mingyang.bootlaunch.common.utils.WechatPay2ValidatorForRequest;
import com.mingyang.bootlaunch.service.WxPayService;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description: 微信相关接口
 * @date: 2022/9/12 15:41
 * @version: 1.0
 */
@Api(tags = "网站微信支付api")
@RestController
@RequestMapping("/api/wx-pay")
public class WxPayController {

    @Resource
    private WxPayConfig wxPayConfig;

    @Resource
    private WxPayService wxPayService;

    @Resource
    private Verifier verifier;

    @ApiOperation(value="获取微信配置文件",notes ="获取微信配置文件")
    @GetMapping("/config")
    public Result getWxPayConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put("mchId",wxPayConfig.getMchId());
        map.put("mchSerialNo",wxPayConfig.getMchSerialNo());
        String jsonString = JSONObject.toJSONString(wxPayConfig.getPrivateKey(wxPayConfig.getPrivateKeyPath()));
        Object parse = JSONObject.parse(jsonString);
        map.put("key", parse);
        return Result.success(map);
    }


    /**
     * 生成支付二维码
     * @return
     */
    @ApiOperation("调用统一下单API,生成支付二维码")
    @PostMapping("native/{productId}")
    public Result nativePay(@PathVariable Long productId) throws IOException {
        Map<String, Object> map =  wxPayService.nativePay(productId);
        return Result.success(map);
    }

    @PostMapping("/native/notify")
    public String nativeNotify(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        try {
            // 处理通知参数
            String body = HttpUtils.readData(request);
            Map<String,Object> bodyJson = gson.fromJson(body, HashMap.class);
            System.out.println("支付通知的id ========>> "+ bodyJson.get("id"));
            System.out.println("支付通知的完整数据 ========>> "+ bodyJson);
            String resultId = (String) bodyJson.get("id");

            // 签名验证
            WechatPay2ValidatorForRequest wechatPay2ValidatorForRequest = new WechatPay2ValidatorForRequest(verifier, body, resultId);
            if(!wechatPay2ValidatorForRequest.validate(request)) {

                System.out.println("=========>>>>> 通知验签失败");
                // 失败应答
                response.setStatus(500);
                map.put("code","ERROR");
                map.put("message","通知验签失败");
                return gson.toJson(map);
            }
            // 处理订单
            System.out.println("=========>>>>> 通知验签成功");
            wxPayService.processOrder(bodyJson);

            // 成功应答
            response.setStatus(200);
            map.put("code","SUCCESS");
            map.put("message","成功");
            return gson.toJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            // 失败应答
            response.setStatus(500);
            map.put("code","ERROR");
            map.put("message","失败");
            return gson.toJson(map);
        }
    }
}
