package com.pine.admin.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.base.entity.Cities;

/**
 * 城市信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
public interface CitiesDao extends BaseDao<Cities> {

    List<Cities> queryAllCities(Cities record);

    List<Cities> queryCitiessByPage(Map<String, Object> parameter);

    Cities queryCities(Cities record);

    String queryCitiesName(Integer cityid);

}
