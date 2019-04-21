package com.pine.admin.modules.base.controller;

import com.pine.admin.config.WeChatConfig;
import com.pine.admin.modules.base.service.WeiXinService;
import com.pine.admin.shiro.ShiroUtils;
import com.pine.common.dto.Result;
import com.pine.common.utils.HttpRequest;
import com.pine.common.wxpay.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Pine
 * @Date: 2019/4/2
 * @Email:771190883@qq.com
 */
@RestController
@RequestMapping("/wxPay")
@Slf4j
@Api(value = "微信支付接口", tags = {"微信支付接口" })

public class WxPayController {
    @Autowired
    private WeiXinService weiXinService;
    /**
     * @param request
     * @return Map
     * @Description 微信浏览器内微信支付/公众号支付(JSAPI)
     */
    @ApiOperation(value = "微信浏览器内微信支付/公众号支付", notes = "微信浏览器内微信支付/公众号支付")
    @PostMapping(value = "/orders")
    public Result orders(HttpServletRequest request, String orderNumber) throws Exception {

        //TODO 查询订单 添加支付金额

        String openId = ShiroUtils.getWeiXinOpenId();
        //拼接统一下单地址参数
        Map<String, String> paraMap = new HashMap<String, String>();
        //获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }

        paraMap.put("appid", WeChatConfig.APP_ID);
        paraMap.put("body", "锦鲤商城-订单结算");
        paraMap.put("mch_id", WeChatConfig.PAY_MCHID);
        paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
        paraMap.put("openid", openId);
        paraMap.put("out_trade_no", WXPayUtil.generateNonceStr());//订单号
        paraMap.put("spbill_create_ip", ip);
        paraMap.put("total_fee", "100000");
        paraMap.put("trade_type", WeChatConfig.TRADE_TYPE);
        paraMap.put("notify_url", "");// 此路径是微信服务器调用支付结果通知路径随意写
        String sign = WXPayUtil.generateSignature(paraMap, WeChatConfig.PAY_KEY);
        paraMap.put("sign", sign);
        String xml = WXPayUtil.mapToXml(paraMap);//将所有参数(map)转xml格式

        String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        String xmlStr = HttpRequest.sendPost(unifiedorder_url, xml);//发送post请求"统一下单接口"返回预支付id:prepay_id

        //以下内容是返回前端页面的json数据
        String prepay_id = "";//预支付id
        if (xmlStr.indexOf("SUCCESS") != -1) {
            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
            prepay_id = (String) map.get("prepay_id");
        }
        Map<String, String> payMap = new HashMap<String, String>();
        payMap.put("appId", WeChatConfig.APP_ID);
        payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
        payMap.put("nonceStr", WXPayUtil.generateNonceStr());
        //签名方式
        payMap.put("signType", "MD5");
        //订单详情扩展字符串
        payMap.put("package", "prepay_id=" + prepay_id);
        //签名
        String paySign = WXPayUtil.generateSignature(payMap, WeChatConfig.PAY_KEY);
        payMap.put("paySign", paySign);
        return Result.success(true, payMap);

    }

    @ApiOperation(value = "微信回调接口", notes = "微信回调接口")

    @PostMapping(value = "/callback")
    public String callBack(HttpServletRequest request, HttpServletResponse response) {
        log.info("进入微信支付异步通知");
        String resXml = "";
        try {
            //
            InputStream is = request.getInputStream();
            //将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            log.info("微信支付异步通知请求包: {}", resXml);
            return weiXinService.payBack(resXml);
        } catch (Exception e) {
            log.error("微信支付回调通知失败", e);
            String result = "&lt;xml&gt;" + "&lt;return_code&gt;&lt;![CDATA[FAIL]]&gt;&lt;/return_code&gt;" + "&lt;return_msg&gt;&lt;![CDATA[报文为空]]&gt;&lt;/return_msg&gt;" + "&lt;/xml&gt; ";
            return result;
        }

    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(325);
        BigDecimal b = new BigDecimal(20.18);
        System.out.println(String.valueOf(a.multiply(b).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
    }
}

