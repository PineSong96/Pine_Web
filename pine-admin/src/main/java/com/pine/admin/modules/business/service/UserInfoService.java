package com.pine.admin.modules.business.service;

import com.pine.admin.modules.base.binder.BaseService;
import com.pine.admin.modules.business.entity.UserInfo;

/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
public interface UserInfoService extends BaseService<UserInfo> {

    UserInfo getUserInfoByOpenId(String openId);


}

