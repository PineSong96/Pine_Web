package com.pine.admin.modules.base.service.impl;

import com.pine.admin.config.WeChatConfig;
import com.pine.admin.modules.base.service.WeiXinService;
import com.pine.admin.modules.business.dao.UserInfoDao;
import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.shiro.WxOpenIdToken;
import com.pine.common.utils.Constant;
import com.pine.common.utils.HttpGetRequestUtil;
import com.pine.common.wxpay.WXPay;
import com.pine.common.wxpay.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Pine
 * @Date: 2019/3/31
 * @Email:771190883@qq.com
 */
@Service("weiXinService")
@Slf4j
public class WeiXinServiceImpl implements WeiXinService {


    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public boolean weixinLogin(String code) {
        try {

            /**
             * 无缓存用户
             */
            // System.out.println("code : " + code);
            String url = WeChatConfig.OPENID_URL + "appid=" + WeChatConfig.APP_ID + "&secret=" + WeChatConfig.APP_SECRET + "&code=" + code
                    + "&grant_type=authorization_code";
            JSONObject data = HttpGetRequestUtil.loadJSON(url);
            // System.out.println("data : " + data);

            //System.out.println(json.toString());

            String openId = (String) data.get("openid");

            String accessToken = (String) data.get("access_token");

            url = WeChatConfig.USER_INFO_URL +"access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";

            data = HttpGetRequestUtil.loadJSON(url);
            //获得用户信息

            String userName = (String) data.get("nickname");
//            userName = userName.replaceAll("[^\\\\uD000-\\\\uFFFF]", "");
            String headimgurl = (String) data.get("headimgurl");
            //查询微信用户
            UserInfo userInfo = new UserInfo();
            userInfo.setWxOpenid(openId);
            userInfo = userInfoDao.queryOne(userInfo);

            if (null == userInfo) {
                UserInfo recod = new UserInfo();
                recod.setWxOpenid(openId);
                recod.setUserName(userName);
                recod.setUserIcon(headimgurl);
                recod.setId(null);
                userInfoDao.insertSelective(recod);
                Subject subject = SecurityUtils.getSubject();
                //使用自定义realm验证openid是否已绑定用户
                WxOpenIdToken wxOpenIdToken = new WxOpenIdToken(recod.getWxOpenid());
                subject.login(wxOpenIdToken);
                return true;
            } else {
                userInfo.setUserName(userName);
                userInfo.setUserIcon(headimgurl);
                userInfoDao.updateByPrimaryKeySelective(userInfo);
                Subject subject = SecurityUtils.getSubject();
                //使用自定义realm验证openid是否已绑定用户
                WxOpenIdToken wxOpenIdToken = new WxOpenIdToken(userInfo.getWxOpenid());
                subject.login(wxOpenIdToken);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String payBack(String notifyData) {
        log.info("payBack() start, notifyData={}", notifyData);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
            WXPay wxpay = new WXPay(new WeChatConfig());
            // 转换成map
            notifyMap = WXPayUtil.xmlToMap(notifyData);
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                if (WXPayUtil.isSignatureValid(xmlBack, WeChatConfig.PAY_KEY)) {
                    // 签名正确
                    // 进行处理。
                    // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功

                    //状态
                    String return_code = notifyMap.get("return_code");
                    //订单号
                    String out_trade_no = notifyMap.get("out_trade_no");

                    if (out_trade_no == null) {
                        log.info("微信支付回调失败订单号: {}", notifyMap);
                        xmlBack = "&lt;xml&gt;" + "&lt;return_code&gt;&lt;![CDATA[FAIL]]&gt;&lt;/return_code&gt;" + "&lt;return_msg&gt;&lt;![CDATA[报文为空]]&gt;&lt;/return_msg&gt;" + "&lt;/xml&gt; ";
                        return xmlBack;
                    }

                    // 业务逻辑处理 ****************************
                    log.info("微信支付回调成功订单号: {}", notifyMap);

                    //TODO 订单更新支付状态

                    xmlBack = "&lt;xml&gt;" + "&lt;return_code&gt;&lt;![CDATA[SUCCESS]]&gt;&lt;/return_code&gt;" + "&lt;return_msg&gt;&lt;![CDATA[SUCCESS]]&gt;&lt;/return_msg&gt;" + "&lt;/xml&gt; ";
                    return xmlBack;
                }
            } else {
                log.error("微信支付回调通知签名错误");
                xmlBack = "&lt;xml&gt;" + "&lt;return_code&gt;&lt;![CDATA[FAIL]]&gt;&lt;/return_code&gt;" + "&lt;return_msg&gt;&lt;![CDATA[报文为空]]&gt;&lt;/return_msg&gt;" + "&lt;/xml&gt; ";
                return xmlBack;
            }
        } catch (Exception e) {
            log.error("微信支付回调通知失败", e);
            xmlBack = "&lt;xml&gt;" + "&lt;return_code&gt;&lt;![CDATA[FAIL]]&gt;&lt;/return_code&gt;" + "&lt;return_msg&gt;&lt;![CDATA[报文为空]]&gt;&lt;/return_msg&gt;" + "&lt;/xml&gt; ";
        }
        return xmlBack;
    }

}
