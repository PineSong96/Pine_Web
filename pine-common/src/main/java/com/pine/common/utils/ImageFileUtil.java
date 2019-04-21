package com.pine.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Slf4j
public class ImageFileUtil {

    /**
     * 日志对象
     */
    private static String aliid = "LTAIIDHQRUJbvfqy";
    private static String alikey = "cnv2sGOogU59E00BtlMCcMJiYGRFfk";
    private static String aliossendpoit = "http://oss-cn-beijing.aliyuncs.com";
    private static String bucketname = "youlipin";
    private static String aliImageSever = "https://youlipin.oss-cn-beijing.aliyuncs.com/";

    public static String upLoadImage(InputStream fin, String path) {

        log.info("UpLoadImage :上传照片 开始  ： fin：" + fin + "; phth: " + path);

        OSSClient client = new OSSClient(aliossendpoit, aliid, alikey);
        OSSObject object = null;
        log.info("UpLoadImage :上传照片  ： aliid：" + aliid + "; alikey: " + alikey + "; aliossendpoit : " + aliossendpoit + "; bucketname:"
                + bucketname);
        if (path.startsWith("/")) {
            return "invalid path";
        }
        @SuppressWarnings("unused")

        PutObjectResult result = client.putObject(bucketname, path, fin, new ObjectMetadata());

        try {
            object = client.getObject(bucketname, path);
        } catch (Exception r) {
            log.error("UpLoadImage: 上传照片异常 ", r);
        }

        // 如果不设置content-length, 默认为chunked编码。
        if (object != null) {
            return aliImageSever+path;
        }

        return "success";
    }


    public String GetRealImagePath(String Path) {

        if (Path == null || "".equals(Path) || Path.startsWith("images")) {
            return Path;
        }
        return aliImageSever + "/" + Path;
    }

    public static String uploadImg(String base64) throws UnsupportedEncodingException {

        String path = "koi/application/";

        try {
            log.info("uploadIcon:上传图片");

            //图片原名称
            String imgName = "";
            //图片生成名称
            String fileName = "";
            if (base64 == null) {
                return null;
            }
            String type = ".jpg";
            Date nowDate = new Date();
            //格式化时间对象返回字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            fileName += sdf.format(nowDate);
            Random random = new Random();
            String randomCode = "";
            for (int i = 0; i < 8; i++) {
                randomCode += Integer.toString(random.nextInt(36), 36);
            }
            fileName += randomCode;
            fileName += type;
            String virtualPath = "";
            try {
                virtualPath = path + "/" + fileName;
                //item.write(new File(uploadPath + fileName));
                String result = ImageFileUtil.upLoadImage(ImagesUtil.GenerateImage(base64), virtualPath);
                System.out.println("存入照片：" + result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传图片异常");
        }

        return null;

    }

}
