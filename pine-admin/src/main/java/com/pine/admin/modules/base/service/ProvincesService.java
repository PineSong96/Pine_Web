package com.pine.admin.modules.base.service;

import com.pine.admin.modules.base.entity.Provinces;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * 省份信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
public interface ProvincesService {

    List<Provinces> getProvincesList(Provinces record);

    List<Provinces> getProvincesByPage(Page page, Provinces record);

    Provinces getProvincesById(String id);

    Provinces getProvinces(Provinces record);

    boolean createProvinces(Provinces record);

    boolean deleteProvinces(String id);

    boolean deleteProvincesAll(String ids);

    boolean updateProvinces(Provinces record);

}

