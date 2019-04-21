package com.pine.admin.modules.base.service;


import com.pine.common.excelutil.ExcelPoiUtil;
import com.pine.common.exception.ApiException;
import com.pine.common.utils.DateTimeTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
public interface ExcelPoiService {
    void exampleExport(HttpServletRequest request, HttpServletResponse response, Integer productId);
}
