package com.pine.admin.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import com.pine.admin.modules.system.dao.SysUserRoleDao;
import com.pine.admin.modules.system.entity.SysUserRole;
import com.pine.admin.modules.system.service.SysUserRoleService;


@Service("sysUserRoleService")
@Slf4j
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;


    @Override
    public List<SysUserRole> getSysUserRoleList(SysUserRole record) {

        log.info("getSysUserRoleList");
        try {

            return sysUserRoleDao.queryAllSysUserRole(record);
        } catch (Exception e) {
            log.error("getSysUserRoleList", e);
        }

        return null;
    }

    @Override
    public List<SysUserRole> getSysUserRoleByPage(Page page, SysUserRole record) {

        log.info("getSysUserRoleByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return sysUserRoleDao.querySysUserRolesByPage(parameter);
        } catch (Exception e) {
            log.error("getSysUserRoleByPage", e);
        }

        return null;
    }

    @Override
    public SysUserRole getSysUserRoleById(String id) {

        log.info("getSysUserRoleById");
        try {

            return sysUserRoleDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getSysUserRoleById", e);
        }

        return null;
    }

    @Override
    public SysUserRole getSysUserRole(SysUserRole record) {

        log.info("getSysUserRole");
        try {

            return sysUserRoleDao.querySysUserRole(record);
        } catch (Exception e) {
            log.error("getSysUserRole", e);
        }

        return null;
    }

    @Override
    public boolean createSysUserRole(SysUserRole record) {

        log.info("createSysUserRole");
        try {

            int updates = sysUserRoleDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysUserRole", e);
        }

        return false;
    }

    @Override
    public boolean deleteSysUserRole(String id) {

        log.info("deleteSysUserRole");
        try {

            int updates = sysUserRoleDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteSysUserRole", e);
        }

        return false;
    }

    @Override
    public boolean updateSysUserRole(SysUserRole record) {

        log.info("updateSysUserRole");
        try {

            int updates = sysUserRoleDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateSysUserRole", e);
        }

        return false;
    }

    @Override
    public boolean delUser(String sysUserId) {

        log.info("delUser");
        try {

            int updates = sysUserRoleDao.delUser(Integer.valueOf(sysUserId));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("delUser", e);
        }
        return false;
    }

    @Override
    public boolean createSysRolePermission(String userId, String roleIds) {
        log.info("createSysRolePermission");
        try {
            int update = 0;

            update += sysUserRoleDao.delUser(Integer.valueOf(userId));

            SysUserRole record = new SysUserRole();

            record.setSysUserId(Integer.valueOf(userId));

            record.setId(null);

            record.setSysRoleId(Integer.valueOf(roleIds));

            update += sysUserRoleDao.insertSelective(record);

            if (update > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysRolePermission", e);
        }

        return false;
    }

    @Override
    public SysUserRole selectByUser(Integer userId) {

        return sysUserRoleDao.selectByUser(userId);
    }
}
