package com.pine.common.utils;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <pre>
 * The Class NetUtils.
 *
 * Description:
 *
 * author: 刘航
 *
 * Revision History:
 * <who>   <when>   <what>
 * 刘航   2013-12-31
 *
 * </pre>
 */
public class NetUtils {

    private static Logger log = Logger.getLogger(NetUtils.class);


    /**
     * <pre>
     * Gets the url response.
     * </pre>
     *
     * @param uri   ？之前一部分
     * @param param GET方式？后面的参数
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     * @author 刘航 2013-12-31
     */
    public static String postUrlResponse(String uri, String param) throws IOException {

        URL url = null;
        InputStream stream = null;
        ByteArrayOutputStream outSteam = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.connect();
            connection.getOutputStream().write(param.getBytes());
            stream = connection.getInputStream();

            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = stream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            result = outSteam.toString("utf-8");
        } catch (IOException e) {
            log.error("", e);
            throw e;
        } finally {
            if (null != outSteam) {
                outSteam.close();
            }
            if (null != stream) {
                stream.close();
            }
            connection.disconnect();
        }
        return result;
    }


    public static String getUrlResponse(String uri, String param) throws IOException {

        URL url = null;
        InputStream stream = null;
        ByteArrayOutputStream outSteam = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.connect();
            connection.getOutputStream().write(param.getBytes());
            stream = connection.getInputStream();

            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = stream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            result = outSteam.toString("utf-8");
        } catch (IOException e) {
            log.error("", e);
            throw e;
        } finally {
            if (null != outSteam) {
                outSteam.close();
            }
            if (null != stream) {
                stream.close();
            }
            connection.disconnect();
        }
        return result;
    }

}
