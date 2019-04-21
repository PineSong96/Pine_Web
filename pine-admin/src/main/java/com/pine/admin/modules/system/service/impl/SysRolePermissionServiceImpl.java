package com.pine.admin.modules.system.service.impl;

import com.pine.admin.modules.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;

import com.pine.admin.modules.system.dao.SysRolePermissionDao;
import com.pine.admin.modules.system.entity.SysRolePermission;


@Service("sysRolePermissionService")
@Slf4j
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;


    @Override
    public List<SysRolePermission> getSysRolePermissionList(SysRolePermission record) {

        log.info("getSysRolePermissionList");
        try {

            return sysRolePermissionDao.queryAllSysRolePermission(record);
        } catch (Exception e) {
            log.error("getSysRolePermissionList", e);
        }

        return null;
    }

    @Override
    public List<SysRolePermission> getSysRolePermissionByPage(Page page, SysRolePermission record) {

        log.info("getSysRolePermissionByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return sysRolePermissionDao.querySysRolePermissionsByPage(parameter);
        } catch (Exception e) {
            log.error("getSysRolePermissionByPage", e);
        }

        return null;
    }

    @Override
    public SysRolePermission getSysRolePermissionById(String id) {

        log.info("getSysRolePermissionById");
        try {

            return sysRolePermissionDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getSysRolePermissionById", e);
        }

        return null;
    }

    @Override
    public SysRolePermission getSysRolePermission(SysRolePermission record) {

        log.info("getSysRolePermission");
        try {

            return sysRolePermissionDao.querySysRolePermission(record);
        } catch (Exception e) {
            log.error("getSysRolePermission", e);
        }

        return null;
    }

    @Override
    public boolean createSysRolePermission(SysRolePermission record) {

        log.info("createSysRolePermission");
        try {

            int updates = sysRolePermissionDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createSysRolePermission", e);
        }

        return false;
    }

    @Override
    public boolean createSysRolePermission(String roleId, String perIds) {

        log.info("createSysRolePermission");
        try {
            SysRolePermission record = new SysRolePermission();
            record.setSysRoleId(Integer.valueOf(roleId));
            String[] ids = perIds.split(",");
            int update = 0;
            for (String id : ids) {
                record.setId(null);

                record.setSysPermissionId(Integer.valueOf(id));

                update += sysRolePermissionDao.insertSelective(record);

                if (update == ids.length) {
                    return true;
                }
            }

        } catch (Exception e) {
            log.error("createSysRolePermission", e);
        }
        return false;
    }

    @Override
    public boolean deleteSysRolePermission(String id) {

        log.info("deleteSysRolePermission");
        try {

            int updates = sysRolePermissionDao.delete(id);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteSysRolePermission", e);
        }

        return false;
    }

    @Override
    public boolean updateSysRolePermission(SysRolePermission record) {

        log.info("updateSysRolePermission");
        try {

            int updates = sysRolePermissionDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateSysRolePermission", e);
        }

        return false;
    }

    @Override
    public boolean delSysRole(String sysRoleId) {

        log.info("delSysRole");
        try {

            int updates = sysRolePermissionDao.delRole(Integer.valueOf(sysRoleId));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("delSysRole", e);
        }

        return false;
    }

    @Override
    public boolean createRolePer(SysRolePermission record) {
        try {
            //删除之前的权限
            int updates = sysRolePermissionDao.delRole(record.getSysRoleId());

            //更新新的权限
            SysRolePermission srp = new SysRolePermission();
            record.setSysRoleId(record.getSysRoleId());
            String[] ids = record.getPerIds().split(",");

            for (String id : ids) {
                record.setId(null);

                record.setSysPermissionId(Integer.valueOf(id));

                updates += sysRolePermissionDao.insertSelective(record);

                if (updates == (ids.length + 1)) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("delSysRole", e);
        }

        return false;
    }

}
