package com.pine.admin.modules.system.service.impl;

import com.pine.admin.modules.system.dao.SysPermissionDao;
import com.pine.admin.modules.system.entity.ActiveUser;
import com.pine.admin.modules.system.entity.SysPermission;
import com.pine.admin.modules.system.service.SysPermissionService;
import com.pine.admin.shiro.ShiroUserInfo;
import com.pine.admin.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Service("sysPermissionService")
@Slf4j
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;


    @Override
    public List<SysPermission> getSysPermissionList(SysPermission record) {

        log.info("getSysPermissionList");
        try {

            return sysPermissionDao.queryAllSysPermission(record);
        } catch (Exception e) {
            log.error("getSysPermissionList", e);
        }

        return null;
    }

    @Override
    public List<SysPermission> getSysPermissionByPage(Page page, SysPermission record) {

        log.info("getSysPermissionByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return sysPermissionDao.querySysPermissionsByPage(parameter);
        } catch (Exception e) {
            log.error("getSysPermissionByPage", e);
        }

        return null;
    }

    @Override
    public SysPermission getSysPermissionById(String id) {

        log.info("getSysPermissionById");
        try {

            return sysPermissionDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getSysPermissionById", e);
        }

        return null;
    }

    @Override
    public SysPermission getSysPermission(SysPermission record) {

        log.info("getSysPermission");
        try {

            return sysPermissionDao.querySysPermission(record);
        } catch (Exception e) {
            log.error("getSysPermission", e);
        }

        return null;
    }

    @Override
    public boolean createSysPermission(SysPermission record) {

        log.info("createSysPermission");
        try {

            int updates = sysPermissionDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysPermission", e);
        }

        return false;
    }

    @Override
    public boolean deleteSysPermission(String id) {

        log.info("deleteSysPermission");
        try {

            int updates = sysPermissionDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteSysPermission", e);
        }

        return false;
    }

    @Override
    public boolean updateSysPermission(SysPermission record) {

        log.info("updateSysPermission");
        try {

            int updates = sysPermissionDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateSysPermission", e);
        }

        return false;
    }

    @Override
    public List<SysPermission> getSysMenu(HttpServletRequest request, HttpServletResponse response) {
        log.info("getSysMenu");
        try {
            ShiroUserInfo shiroUserInfo = ShiroUtils.getShiroUserInfo();
            //从Session中取出菜单范围的URL
            List<SysPermission> menus = shiroUserInfo.getMenus();
            return menus;
        } catch (Exception e) {
            log.info("getSysMenu");
        }
        return null;
    }


}
