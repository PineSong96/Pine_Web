package com.pine.admin.modules.base.binder;

import com.pine.common.dto.Page;

import java.util.List;

/**
 * @Author: Pine
 * @Date: 2019/4/18
 * @Email:771190883@qq.com
 */
public interface BaseService<T> {
    List<T> getAllList(T record);

    List<T> getByPage(Page page, T record);

    T getById(String id);

    T getOne(T record);

    boolean create(T record);

    boolean delete(String id);

    boolean deltetByIds(String ids);

    boolean update(T record);
}
