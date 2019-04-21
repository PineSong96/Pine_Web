package com.pine.admin.modules.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import org.springframework.transaction.annotation.Transactional;

import com.pine.admin.modules.business.dao.UserInfoDao;
import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.business.service.UserInfoService;


@Service("userInfoService")
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserInfo> getAllList(UserInfo record) {

        log.info("getAllList");
        try {

            return userInfoDao.queryAll(record);
        } catch (Exception e) {
            log.error("getAllList", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserInfo> getByPage(Page page, UserInfo record) {

        log.info("getByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return userInfoDao.queryByPage(parameter);
        } catch (Exception e) {
            log.error("getByPage", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo getById(String id) {

        log.info("getById");
        try {

            return userInfoDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getById", e);
        }

        return null;
    }

    @Override
    public UserInfo getUserInfoByOpenId(String openId) {
        log.info("getById");
        try {

            return userInfoDao.selectByOpenId(openId);
        } catch (Exception e) {
            log.error("getById", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo getOne(UserInfo record) {

        log.info("getOne");
        try {

            return userInfoDao.queryOne(record);
        } catch (Exception e) {
            log.error("getOne", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(UserInfo record) {

        log.info("create");
        try {

            int updates = userInfoDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("create", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {

        log.info("delete");
        try {

            int updates = userInfoDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("delete", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deltetByIds(String ids) {

        log.info("deleteeUserInfoAll");
        try {

            String[] id = ids.split(";");
            int updates = userInfoDao.deleteIds(id);

            if (id.length == updates) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteeUserInfoAll", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UserInfo record) {

        log.info("update");
        try {

            int updates = userInfoDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("update", e);
        }

        return false;
    }

}
