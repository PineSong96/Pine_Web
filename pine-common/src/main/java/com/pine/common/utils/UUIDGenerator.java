package com.pine.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author Administrator
 */
public class UUIDGenerator {
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 获取默认UUID
     *
     * @return
     */
    public static String genUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取UUID去除"-"
     *
     * @return
     */
    public static String genUUIDRemoveSep() {
        String uuid = UUID.randomUUID().toString();
        // 去掉"-"符号
        return uuid.replaceAll("-", "");
    }

    /**
     * 获得指定数量的UUID
     *
     * @param number
     * @return
     */
    public static String[] genUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = genUUID();
        }
        return ss;
    }

    /**
     * 获得指定数量的UUID 去除"-"
     *
     * @param number
     * @return
     */
    public static String[] genUUIDRemoveSep(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = genUUIDRemoveSep();
        }
        return ss;
    }


    /**
     * 获取8位的UUID
     *
     * @return
     */
    public static String genShortUuid(Integer size) {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < size; i++) {
            String str = uuid.substring(i * size, i * size + size);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    public static String getOrderIdByTime(Integer userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + userId + result;
    }


}
