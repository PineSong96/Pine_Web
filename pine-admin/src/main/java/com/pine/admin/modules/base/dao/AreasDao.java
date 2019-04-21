package com.pine.admin.modules.base.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.base.entity.Areas;

/**
 * 区县信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:56
 */
public interface AreasDao extends BaseDao<Areas> {

    List<Areas> queryAllAreas(Areas record);

    List<Areas> queryAreassByPage(Map<String, Object> parameter);

    Areas queryAreas(Areas record);

    String queryAreasName(Integer areaid);

}
