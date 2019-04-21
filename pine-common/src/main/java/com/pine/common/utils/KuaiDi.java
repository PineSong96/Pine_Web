package com.pine.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pine.common.dto.Result;
import com.pine.common.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Pine
 * @Date: 2019/4/10
 * @Email:771190883@qq.com
 */
public class KuaiDi {


    public static String key = "JjydlyGo2107";
    public static String customer = "9A030F7A0EBCDCB272D9A58A5668F4CF";
    public static String callurl = "http://www.yourdmain.com/kuaidi";

    public static String kuaidiInfo(String excode, String exnum, String phone) {

        String param = "{\"com\":\"" + excode + "\",\"num\":\"" + exnum + "\",\"phone\":\"" + phone + "\"}";
        String sign = MD5Util.encode(param + key + customer);
        HashMap params = new HashMap();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
//        String info = "param="+ JSON.toJSONString(param) + "&sign=" +sign +"&customer=" + customer;
        String resp;
//        JSONObject.parseJ
        try {
            resp = PostUtil.httpPost("https://poll.kuaidi100.com/poll/query.do", params);
            System.out.println(JSON.parse(resp));
            return resp;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean kuaidiReceiver(String kuadiname, String from, String to, String kuadicode) {
        TaskRequest req = new TaskRequest();
        req.setCompany(kuadiname);
        req.setFrom(from);
        req.setTo(to);
        req.setNumber(kuadicode);
        req.getParameters().put("callbackurl", callurl);
        req.setKey(key);
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("schema", "json");
        p.put("param", JSON.toJSONString(req));
        try {
            String ret = PostUtil.httpPost("https://poll.kuaidi100.com/poll", p);
//            Object parse = JSON.parse(ret);
//            System.out.println(ret);
            TaskResponse resp = JSON.parseObject(ret, TaskResponse.class);
            if ("true".equals(resp.getResult())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        kuaidiReceiver();
    }


}

@Getter
@Setter
class TaskRequest {
    private String company;
    private String from;
    private String to;
    private String number;
    private String key;
    private HashMap<String, String> parameters = new HashMap<>();

}

@Getter
@Setter
class TaskResponse {
    private String result;
    private String returnCode;
    private String message;
    private HashMap<String, String> parameters = new HashMap<>();

}
