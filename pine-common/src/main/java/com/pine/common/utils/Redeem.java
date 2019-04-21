package com.pine.common.utils;

import java.util.*;

/**
 * @Author: Pine
 * @Date: 2019/3/30
 * @Email:771190883@qq.com
 */

public class Redeem {
    private static final String Base32Alphabet = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    /**
     * 生成新的序列号    <br>
     * <p>生成规则：45位的数 （二进制）<br>
     * 标识位  + 数据位 + 校验位 <br>
     * 然后将55位的数映射到用 ABCDEFGHJKLMNPQRSTUVWXYZ23456789 表示的序列号，要映射到32个字符中就是每5位代表一个字符(2^5=32)，
     * 所有生成的序列号是 45/5=9位。
     *
     * @param codeLen     code长度
     * @param flag        标识
     * @param flagBitLen  标识长度
     * @param checkBitLen 校验位长度
     * @return
     */
    public static String generateNewCode(int codeLen, int flag, int flagBitLen, int checkBitLen) {
        Long ret = 0L; // 长整形ID
        Random random = new Random();
        int checkModData = 1 << checkBitLen;
        int totalBitLen = codeLen * 5;
        int dataBitLen = totalBitLen - checkBitLen - flagBitLen;
        long randData = (long) (1 + (1L << dataBitLen - 1) * random.nextDouble());
        if (flagBitLen > 0) {
            flag = flag & ((1 << flagBitLen) - 1);                    //防止越位，若16位标识则是 0xffff
            ret += (long) flag << (totalBitLen - flagBitLen);        //高位标志位
        }

        ret += randData << checkBitLen;                         // 中位数据位
        long checkNum = (ret >> checkBitLen) % checkModData;    //低位校验位
        ret += checkNum; // 1 - 7位 校验位
        return convertToBase32SerialCode(ret, codeLen);
    }

    public static String generateNewCode(int flag, int flagBitLen, int length) {
        return generateNewCode(length, flag, flagBitLen, 15);        //生成码9位，活动id 16位
    }

    public static String generateNewCode(int flag) {
        int flagBitLen = 0;
        if (flag == 0) {
            flagBitLen = 0;
        } else {
            flagBitLen = Integer.toBinaryString(flag).length();
        }
        return generateNewCode(9, flag, flagBitLen, 7);        //生成码9位
    }

    public static String generateNewCode() {
        return generateNewCode(9, 0, 0, 7);                    //生成码9位
    }

    /**
     * @param historyCodeSet 历史生成的序列号 集合
     * @param number
     * @param codeLen
     * @param flag
     * @param flagBitLen
     * @param checkBitLen
     * @return
     */
    public static Set<String> generateCodes(Set<String> historyCodeSet, int number, int codeLen, int flag, int flagBitLen, int checkBitLen) {
        Set<String> generatedCodes = new HashSet<String>(number * 4 / 3 + 1);
        if (historyCodeSet == null) {
            historyCodeSet = new HashSet<String>(0);
        }
        while (generatedCodes.size() < number) {
            String code = generateNewCode(codeLen, flag, flagBitLen, checkBitLen);
            if (!historyCodeSet.contains(code)) {
                generatedCodes.add(code);
            }
            System.out.println(code);
        }
        return generatedCodes;
    }


    /**
     * @param historyCodeSet
     * @param number
     * @return
     */
    public static Set<String> generateCodes(Set<String> historyCodeSet, int number, int codeLen) {
        return generateCodes(historyCodeSet, number, codeLen, 0, 0, 7);
    }

    /**
     * @param historyCodeSet
     * @param number
     * @return
     */
    public static Set<String> generateCodes(Set<String> historyCodeSet, int number) {
        return generateCodes(historyCodeSet, number, 9, 0, 0, 7);
    }


    /**
     * 将随机数转换成BASE32编码 序列码
     *
     * @return
     */
    private static String convertToBase32SerialCode(long longRandValue, int codeLen) {
        StringBuffer codeSerial = new StringBuffer(16);
        long tmpRandValue = longRandValue;
        for (int i = 0; i < codeLen; i++) {
            int code = (int) (tmpRandValue & 0x1F);
            char convertCode = Base32Alphabet.charAt(code);
            codeSerial.append(convertCode);
            tmpRandValue = tmpRandValue >> 5;
        }
        return codeSerial.reverse().toString();
    }


    /**
     * 将兑换码序列字符转化成数字。
     *
     * @return
     */
    private static int convertBase32CharToNum(char ch) {
        int index = Base32Alphabet.indexOf(ch);
        return index;
    }

    /**
     * 将序列号转成长整数
     *
     * @return
     */
    public static long convertBase32CharToNum(String serialCode) {
        long id = 0;

        for (int i = 0; i < serialCode.length(); i++) {
            int originNum = convertBase32CharToNum(serialCode.charAt(i));
            if (originNum == -1) {
                return 0;
            }
            id = id << 5;
            id += originNum;
        }
        return id;
    }

    /**
     * 校验序列号是否合法
     *
     * @param code
     * @return
     */
    public static boolean checkCodeValid(String code, int checkBitLen) {
        long id = 0;
        int checkModData = 1 << checkBitLen;
        for (int i = 0; i < code.length(); ++i) {
            long originNum = convertBase32CharToNum(code.charAt(i));
            if (originNum >= 32) {
                return false; // 字符非法
            }
            id = id << 5;
            id += originNum;
        }

        long data = id >> checkBitLen;
        long checkNum = id & (checkModData - 1); // 最后7位是校验码

        if (data % checkModData == checkNum) {
            return true;
        }

        return false;
    }

    public static boolean checkCodeValid(String code) {
        if (code == null || code.length() == 0) {
            return false;
        }
        return checkCodeValid(code, 7);
    }

    /**
     * 从序列号提取标识
     *
     * @param code       序列号
     * @param flagBitLen 标识位长度
     * @return
     */
    public static Long getFlagFromCode(String code, int flagBitLen) {
        long id = convertBase32CharToNum(code);
        return id >> (code.length() * 5 - flagBitLen);
    }

    public static void main(String[] args) {
//        System.out.println(checkCodeValid("ARXX2BWTE"));
//        long sTime = System.currentTimeMillis();
//        long eTime = 0L;
//
//        Set<String> codes = generateCodes(null, 1, 10, 3, 2, 7);
//        eTime = System.currentTimeMillis();
//        System.out.println("耗时 " + (eTime-sTime)/1000 + "秒");
//        sTime = eTime;
//
//        Set<String> codes2 = generateCodes(codes, 2, 10, 0, 0, 7);
//        codes2.size();
//        eTime = System.currentTimeMillis();
//        System.out.println("耗时 " + (eTime-sTime)/1000 + "秒");
//
//        String code = generateNewCode(1,10,9);
//        System.out.println("序列号: "+code);
//        boolean checkRs = checkCodeValid(code);
//        System.out.println("序列号" + code + "是否合法：" + checkRs);
//        long acId = getFlagFromCode(code,10);
//        long acId1=getFlagFromCode("5DJX9MXCJU",2);
//        System.out.println("标识: " + acId);
//        System.out.println("标识: " + acId1);
//        long numCode = convertBase32CharToNum(code);
//        System.out.println("数字序列号 " + numCode);
//        System.out.println("================================");
//        checkRs = checkCodeValid("AABLTSBJ");
//        System.out.println("序列号" + "AABLTSBAJ" + "是否合法：" + checkRs);
        ShowTime();
        System.out.println("=======================");
        create((byte) 1, 10000, 12, password);

        VerifyCode("c8dksqjamaba");
        VerifyCode("4a36g5npamna");
        VerifyCode("7jqgdgxg7bna");
        VerifyCode("pm4ggqepp5da");
        VerifyCode("ga8ehxsq6dja");

    }

    /**
     * 实现方法二
     */
    static String stringtable = "abcdefghijkmnpqrstuvwxyz23456789";
    public final static String password = "dak3le2";

    //从byte转为字符表索引所需要的位数
    final static int convertByteCount = 5;

    /**
     * 生成兑换码
     * 这里每一次生成兑换码的最大数量为int的最大值即2147483647
     *
     * @param time
     * @param id
     * @param count
     * @return
     */
    public static List<String> create(byte groupid, int codecount, int codelength, String password) {
        List<String> redeemlist = new ArrayList<>();
        //8位的数据总长度
        int fullcodelength = codelength * convertByteCount / 8;
        //随机码对时间和id同时做异或处理
        //类型1，id4，随机码n,校验码1
        int randcount = fullcodelength - 6;//随机码有多少个

        //如果随机码小于0 不生成
        if (randcount <= 0) {
            return null;
        }
        for (int i = 0; i < codecount; i++) {
            //这里使用i作为code的id
            //生成n位随机码
            byte[] randbytes = new byte[randcount];
            for (int j = 0; j < randcount; j++) {
                randbytes[j] = (byte) (Math.random() * Byte.MAX_VALUE);
            }

            //存储所有数据
            ByteHapper byteHapper = ByteHapper.CreateBytes(fullcodelength);
            byteHapper.AppendNumber(groupid).AppendNumber(i).AppendBytes(randbytes);

            //计算校验码 这里使用所有数据相加的总和与byte.max 取余
            byte verify = (byte) (byteHapper.GetSum() % Byte.MAX_VALUE);
            byteHapper.AppendNumber(verify);

            //使用随机码与时间和ID进行异或
            for (int j = 0; j < 5; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ (byteHapper.bytes[5 + j % randcount]));
            }

            //使用密码与所有数据进行异或来加密数据
            byte[] passwordbytes = password.getBytes();
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                byteHapper.bytes[j] = (byte) (byteHapper.bytes[j] ^ passwordbytes[j % passwordbytes.length]);
            }

            //这里存储最终的数据
            byte[] bytes = new byte[codelength];

            //按6位一组复制给最终数组
            for (int j = 0; j < byteHapper.bytes.length; j++) {
                for (int k = 0; k < 8; k++) {
                    int sourceindex = j * 8 + k;
                    int targetindex_x = sourceindex / convertByteCount;
                    int targetindex_y = sourceindex % convertByteCount;
                    byte placeval = (byte) Math.pow(2, k);
                    byte val = (byte) ((byteHapper.bytes[j] & placeval) == placeval ? 1 : 0);
                    //复制每一个bit
                    bytes[targetindex_x] = (byte) (bytes[targetindex_x] | (val << targetindex_y));
                }
            }

            StringBuilder result = new StringBuilder();
            //编辑最终数组生成字符串
            for (int j = 0; j < bytes.length; j++) {
                result.append(stringtable.charAt(bytes[j]));
            }
            redeemlist.add(result.toString());
//            System.out.println("out string : " + result.toString());
        }
        ShowTime();
        return redeemlist;
    }

    /**
     * 验证兑换码
     *
     * @param code
     */
    public static boolean VerifyCode(String code) {
        byte[] bytes = new byte[code.length()];

        //首先遍历字符串从字符表中获取相应的二进制数据
        for (int i = 0; i < code.length(); i++) {
            byte index = (byte) stringtable.indexOf(code.charAt(i));
            bytes[i] = index;
        }

        //还原数组
        int fullcodelength = code.length() * convertByteCount / 8;
        int randcount = fullcodelength - 6;//随机码有多少个

        byte[] fullbytes = new byte[fullcodelength];
        for (int j = 0; j < fullbytes.length; j++) {
            for (int k = 0; k < 8; k++) {
                int sourceindex = j * 8 + k;
                int targetindex_x = sourceindex / convertByteCount;
                int targetindex_y = sourceindex % convertByteCount;

                byte placeval = (byte) Math.pow(2, targetindex_y);
                byte val = (byte) ((bytes[targetindex_x] & placeval) == placeval ? 1 : 0);

                fullbytes[j] = (byte) (fullbytes[j] | (val << k));
            }
        }

        //解密，使用密码与所有数据进行异或来加密数据
        byte[] passwordbytes = password.getBytes();
        for (int j = 0; j < fullbytes.length; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ passwordbytes[j % passwordbytes.length]);
        }

        //使用随机码与时间和ID进行异或
        for (int j = 0; j < 5; j++) {
            fullbytes[j] = (byte) (fullbytes[j] ^ (fullbytes[5 + j % randcount]));
        }

        //获取校验码 计算除校验码位以外所有位的总和
        int sum = 0;
        for (int i = 0; i < fullbytes.length - 1; i++) {
            sum += fullbytes[i];
        }
        byte verify = (byte) (sum % Byte.MAX_VALUE);

        //校验
        if (verify == fullbytes[fullbytes.length - 1]) {
            System.out.println(code + " : verify success!");
            return true;
        } else {
            System.out.println(code + " : verify failed!");
            return false;
        }

    }


    public static void ShowTime() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        System.out.println("time  : " + times);
    }

}