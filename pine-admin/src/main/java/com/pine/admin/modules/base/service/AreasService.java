package com.pine.admin.modules.base.service;

import com.pine.admin.modules.base.entity.Areas;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * 区县信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:56
 */
public interface AreasService {

    List<Areas> getAreasList(Areas record);

    List<Areas> getAreasByPage(Page page, Areas record);

    Areas getAreasById(String id);

    Areas getAreas(Areas record);

    boolean createAreas(Areas record);

    boolean deleteAreas(String id);

    boolean deleteAreasAll(String ids);

    boolean updateAreas(Areas record);

}

