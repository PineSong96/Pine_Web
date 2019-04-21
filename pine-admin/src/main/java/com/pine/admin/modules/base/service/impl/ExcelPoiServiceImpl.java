package com.pine.admin.modules.base.service.impl;


import com.pine.admin.modules.base.service.ExcelPoiService;
import com.pine.common.excelutil.ExcelPoiUtil;
import com.pine.common.exception.ApiException;
import com.pine.common.utils.DateTimeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
@Service
@Slf4j
public class ExcelPoiServiceImpl implements ExcelPoiService {

    @Override
    public void exampleExport(HttpServletRequest request, HttpServletResponse response, Integer productId) {
        try {
            List<Object> exchangeCodes = new ArrayList<>();
            if (!exchangeCodes.isEmpty()) {
                String excelName = "示例" + DateTimeTool.dateTimeToStr(new Date());
                LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
                fieldMap.put("name", "姓名");
                fieldMap.put("age", "年龄");
                ExcelPoiUtil.export(excelName, exchangeCodes, fieldMap, response);
            }
        } catch (Exception e) {
            log.error("exampleExport", e);

            throw new ApiException(e.getMessage());
        }
    }


}
