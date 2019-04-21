package com.pine.admin.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.base.entity.Provinces;

/**
 * 省份信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
public interface ProvincesDao extends BaseDao<Provinces> {


    List<Provinces> queryAllProvinces(Provinces record);

    List<Provinces> queryProvincessByPage(Map<String, Object> parameter);

    Provinces queryProvinces(Provinces record);

    String queryProvincesName(Integer provinceid);
}
