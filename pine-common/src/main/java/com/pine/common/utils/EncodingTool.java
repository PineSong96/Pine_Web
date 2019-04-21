package com.pine.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class EncodingTool {

    public static String encodeStr(String str) {

        if (str == null) {
            return null;
        }
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String encodeUtfToGbk(String str) {

        if (str == null) {
            return null;
        }
        try {
            String utf8 = new String(str.getBytes("UTF-8"));
            String unicode = new String(utf8.getBytes(), "UTF-8");
            return new String(unicode.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * password密码md5Hash
     *
     * @param orgString
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String pwmd5Encrypt(String orgString) {

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(orgString.getBytes());
            byte[] b = md.digest();
            return byte2hex(b).substring(4, 28);
        } catch (java.security.NoSuchAlgorithmException ne) {
            throw new IllegalStateException("System doesn't support your  Algorithm.");
        }
    }


    public static String getRandomString(int length) { //length表示生成字符串的长度

        String base = "weiqiuwang";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 将字节数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) // 二行制转字符串

    {

        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }

        }
        return hs;
    }


    /**
     * <p>在保存cookie之前把值编码下。</p>
     *
     * @param value
     * @return
     */
    public static String encide(String value) {

        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * <p>取cookie值时解码。</p>
     *
     * @param value
     * @return
     */
    public static String decide(String value) {

        try {
            URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     */
    public static String UnicodeToUtf8(String str) {
        Pattern pattern = compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch + "");

        }

        return str;
    }


}
