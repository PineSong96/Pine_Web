package com.pine.admin.modules.base.service;

import com.pine.admin.modules.base.entity.Cities;

import java.util.List;

import com.pine.common.dto.Page;

/**
 * 城市信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
public interface CitiesService {

    List<Cities> getCitiesList(Cities record);

    List<Cities> getCitiesByPage(Page page, Cities record);

    Cities getCitiesById(String id);

    Cities getCities(Cities record);

    boolean createCities(Cities record);

    boolean deleteCities(String id);

    boolean deleteCitiesAll(String ids);

    boolean updateCities(Cities record);

}

