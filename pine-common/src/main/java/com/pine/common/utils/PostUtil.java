package com.pine.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class PostUtil {

    public static String httpPost(String urlStr, Map<String, String> params) {
        URL connect;
        StringBuffer data = new StringBuffer();
        try {
            connect = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);//post不能使用缓存
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            OutputStreamWriter paramout = new OutputStreamWriter(
                    connection.getOutputStream(), "UTF-8");
            String paramsStr = "";   //拼接Post 请求的参数
            for (String param : params.keySet()) {
                paramsStr += "&" + param + "=" + params.get(param);
            }
            if (!paramsStr.isEmpty()) {
                paramsStr = paramsStr.substring(1);
            }
            paramout.write(paramsStr);
            paramout.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }

            paramout.close();
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data.toString();
    }

    public String send() throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd%HH:mm:ss");

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, 3);
        //显示虚拟号码
        String display_cal = "0";

        //当前时间戳
        String time = String.valueOf(System.currentTimeMillis());

        String endDate = simpleDateFormat.format(calendar.getTime());

        System.out.println("time:" + time + "-----------enDate:" + calendar.getTime());
        //Appkey
        String url_A_x = "http://api.open.zhaopin.com/platform/org/get?access_token=551c619ef13c45debe92a64880f5e1cdlzJv&orgid=75620267";

        String json_send_sms = "{\"display_call\":\"" + display_cal + "}";

        URL url = new URL(url_A_x);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setDoOutput(true);

        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStream out = httpURLConnection.getOutputStream();

        out.write(json_send_sms.getBytes("UTF-8"));

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            System.out.println("recv msg:\t" + EncodingTool.UnicodeToUtf8(resultBuffer.toString()));
            return EncodingTool.UnicodeToUtf8(resultBuffer.toString());
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
    }
}
