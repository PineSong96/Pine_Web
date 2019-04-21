package com.pine.common.utils;

import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ImagesUtil {

    //base64字符串转化成图片

    /**
     * <p>base64字符串转化成图片流。</p>
     *
     * @param imgStr
     * @return
     */
    public static InputStream GenerateImage(String imgStr) { //对字节数组字符串进行Base64解码并生成图片

        if (imgStr == null) //图像数据为空
        {
            return null;
        }
        imgStr = imgStr.substring(imgStr.indexOf(',') + 1);

        try {
            //Base64解码
            // byte[] b = Base64.getDecoder().decode(imgStr);
            byte[] b = new BASE64Decoder().decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            InputStream sbs = new ByteArrayInputStream(b);
            //生成jpeg图片
            //            out.write();
            //            out.flush();
            //            out.close();
            //            //生成jpeg图片
            //            InputStream in = null;
            return sbs;
        } catch (Exception e) {
            return null;
        }
    }
}
