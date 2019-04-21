package com.pine.admin.modules.system.service.impl;

import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.system.entity.SysUser;
import com.pine.admin.shiro.ShiroUtils;
import com.pine.common.exception.ApiException;
import com.pine.common.utils.MD5Util;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;

import com.pine.admin.modules.system.dao.SysUserDao;
import com.pine.admin.modules.system.service.SysUserService;


@Service("sysUserService")
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public List<SysUser> getSysUserList(SysUser record) {

        log.info("getSysUserList");
        try {

            return sysUserDao.queryAllSysUser(record);
        } catch (Exception e) {
            log.error("getSysUserList", e);
        }

        return null;
    }

    @Override
    public List<SysUser> getSysUserByPage(Page page, SysUser record) {

        log.info("getSysUserByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return sysUserDao.querySysUsersByPage(parameter);
        } catch (Exception e) {
            log.error("getSysUserByPage", e);
        }

        return null;
    }

    @Override
    @Cacheable(value = "user", key = "#root.targetClass + #username", unless = "#result eq null")
    public SysUser getSysUserById(String id) {
        log.info("getSysUserById");
        try {
            return sysUserDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getSysUserById", e);
        }

        return null;
    }

    @Override
    public SysUser getSysUserByUserCode(String UserCode) {
        log.info("getSysUserByUserCode");
        try {

            return sysUserDao.selectByUserCode(UserCode);
        } catch (Exception e) {
            log.error("getSysUserByUserCode", e);
        }
        return null;
    }

    @Override
    public SysUser getSysUser(SysUser record) {

        log.info("getSysUser");
        try {

            return sysUserDao.querySysUser(record);
        } catch (Exception e) {
            log.error("getSysUser", e);
        }

        return null;
    }

    @Override
    public boolean createSysUser(SysUser record) {

        log.info("createSysUser");
        try {
            SysUser sysUser = sysUserDao.selectByUserCode(record.getUsercode());
            if (sysUser != null) {
                throw new ApiException("此账号已经存在");
            }
            String salt = RandomStringUtils.randomAlphanumeric(20);
            record.setSalt(salt);
            record.setPassword(ShiroUtils.sha256(record.getPassword(), record.getSalt()));
            int updates = sysUserDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysUser", e);
            throw new ApiException(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteSysUser(String id) {

        log.info("deleteSysUser");
        try {

            int updates = sysUserDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteSysUser", e);
        }

        return false;
    }

    @Override
    public boolean updateSysUser(SysUser record) {

        log.info("updateSysUser");
        try {

            SysUser userInfo = sysUserDao.selectByPrimaryKey(record.getId());

            if (!StringUtils.isEmpty(record.getPassword())) {
                if (!record.getPassword().equals(userInfo.getPassword())) {
                    record.setPassword(ShiroUtils.sha256(record.getPassword(), userInfo.getSalt()));
                }
            }
            int updates = sysUserDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateSysUser", e);
        }

        return false;
    }

}
