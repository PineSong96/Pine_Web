package com.pine.admin.modules.base.binder;

import java.util.List;
import java.util.Map;

/**
 * @Author: Pine
 * @Date: 2019/4/17
 * @Email:771190883@qq.com
 */
public interface BaseDao<T> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    List<T> queryAll(T record);

    List<T> queryByPage(Map<String, Object> parameter);

    int delete(Integer id);

    int deleteIds(String[] record);

    T queryOne (T record);
}
