package com.pine.admin.config;

import com.pine.common.wxpay.IWXPayDomain;
import com.pine.common.wxpay.WXPayConfig;
import com.pine.common.wxpay.WXPayConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Author: Pine
 * @Date: 2019/4/2
 * @Email:771190883@qq.com
 */
@Component
@Getter
@Setter
public class WeChatConfig extends WXPayConfig {

    private byte[] certData;
    /**
     * 微信公众号id
     */
    public static String APP_ID = "xxxxx";
    /**
     * 微信公众号密钥id
     */
    public static String APP_SECRET = "xxxxxxxx";
    /**
     * 微信支付商户号
     */
    public static String PAY_MCHID = "xxxxxx";
    /**
     * 商户号对应的密钥
     */
    public static String PAY_KEY = "xxxxxx";
    /**
     * 前端获取code的微信链接
     */
    public static String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    /**
     * 获取openid的链接
     */
    public static String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
    /**
     * 获取openid的链接
     */
    public static String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?";
    /**
     * 微信统一下单链接
     */
    public static String UN_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * jsapi代表公众号支付
     */
    public static String TRADE_TYPE = "JSAPI";

    @Override
    protected String getAppID() {
        return APP_ID;
    }

    @Override
    public String getMchID() {
        return PAY_MCHID;
    }

    @Override
    protected String getKey() {
        return PAY_KEY;
    }

    @Override
    protected InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }


    @Override
    public IWXPayDomain getWXPayDomain() { // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }

}
