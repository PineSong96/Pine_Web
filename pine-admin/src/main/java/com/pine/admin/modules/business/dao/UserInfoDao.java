package com.pine.admin.modules.business.dao;

import java.util.List;
import java.util.Map;

import com.pine.admin.modules.base.binder.BaseDao;
import com.pine.admin.modules.business.entity.UserInfo;

/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
public interface UserInfoDao extends BaseDao<UserInfo> {
    UserInfo selectByOpenId(String wxOpenid);
}
