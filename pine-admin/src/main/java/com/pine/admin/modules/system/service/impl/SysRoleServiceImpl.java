package com.pine.admin.modules.system.service.impl;

import com.pine.admin.modules.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;

import com.pine.admin.modules.system.dao.SysRoleDao;
import com.pine.admin.modules.system.entity.SysRole;


@Service("sysRoleService")
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;


    @Override
    public List<SysRole> getSysRoleList(SysRole record) {

        log.info("getSysRoleList");
        try {

            return sysRoleDao.queryAllSysRole(record);
        } catch (Exception e) {
            log.error("getSysRoleList", e);
        }

        return null;
    }

    @Override
    public List<SysRole> getSysRoleByPage(Page page, SysRole record) {

        log.info("getSysRoleByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return sysRoleDao.querySysRolesByPage(parameter);
        } catch (Exception e) {
            log.error("getSysRoleByPage", e);
        }

        return null;
    }

    @Override
    public SysRole getSysRoleById(String id) {

        log.info("getSysRoleById");
        try {

            return sysRoleDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getSysRoleById", e);
        }

        return null;
    }

    @Override
    public SysRole getSysRole(SysRole record) {

        log.info("getSysRole");
        try {

            return sysRoleDao.querySysRole(record);
        } catch (Exception e) {
            log.error("getSysRole", e);
        }

        return null;
    }

    @Override
    public boolean createSysRole(SysRole record) {

        log.info("createSysRole");
        try {

            int updates = sysRoleDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysRole", e);
        }

        return false;
    }

    @Override
    public boolean deleteSysRole(String id) {

        log.info("deleteSysRole");
        try {

            int updates = sysRoleDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteSysRole", e);
        }

        return false;
    }

    @Override
    public boolean updateSysRole(SysRole record) {

        log.info("updateSysRole");
        try {

            int updates = sysRoleDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateSysRole", e);
        }

        return false;
    }

}
