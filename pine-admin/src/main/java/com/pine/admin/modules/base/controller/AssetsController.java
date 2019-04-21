package com.pine.admin.modules.base.controller;


import com.pine.admin.modules.base.binder.BaseController;
import com.pine.common.dto.Result;
import com.pine.common.utils.DateTimeTool;
import com.pine.common.utils.ImageFileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by pine.
 */
@RestController
@RequestMapping("/assets")
public class AssetsController extends BaseController {


    @PostMapping(value = "/uploadImg")
    public Result uploadImg(@RequestParam(value = "base64", required = true) String base64) {

        try {
            String path = ImageFileUtil.uploadImg(base64);

            return Result.success(true, path);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Result.error("上传失败");
    }


    @PostMapping(value = "/uploadFile")
    public Result uploadFile(HttpServletRequest request) {

        long startTime = System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            String paths = "";

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    try {
                        String date = DateTimeTool.dateTimeToStringYearMMdd(new Date());
                        String path = ImageFileUtil.upLoadImage(file.getInputStream(), "koi/application/"+date +"/" + System.currentTimeMillis()
                                + file.getOriginalFilename().replaceAll(".+\\.", "."));
                        paths += path + ";";
                        System.out.println("pat::::" + path);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Result.error("上传失败");
                    }
                }

            }
            return Result.success(true, paths.split(";"));
        }
        return Result.error("上传失败");
    }
}
