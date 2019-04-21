package com.pine.admin.modules.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import org.springframework.transaction.annotation.Transactional;

import com.pine.admin.modules.base.dao.AreasDao;
import com.pine.admin.modules.base.entity.Areas;
import com.pine.admin.modules.base.service.AreasService;


@Service("areasService")
@Slf4j
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasDao areasDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Areas> getAreasList(Areas record) {

        log.info("getAreasList");
        try {

            return areasDao.queryAllAreas(record);
        } catch (Exception e) {
            log.error("getAreasList", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Areas> getAreasByPage(Page page, Areas record) {

        log.info("getAreasByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return areasDao.queryAreassByPage(parameter);
        } catch (Exception e) {
            log.error("getAreasByPage", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Areas getAreasById(String id) {

        log.info("getAreasById");
        try {

            return areasDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getAreasById", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Areas getAreas(Areas record) {

        log.info("getAreas");
        try {

            return areasDao.queryAreas(record);
        } catch (Exception e) {
            log.error("getAreas", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createAreas(Areas record) {

        log.info("createAreas");
        try {

            int updates = areasDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createAreas", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAreas(String id) {

        log.info("deleteAreas");
        try {

            int updates = areasDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteAreas", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAreasAll(String ids) {

        log.info("deleteeAreasAll");
        try {

            String[] id = ids.split(";");
            int updates = areasDao.deleteIds(id);

            if (id.length == updates) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteeAreasAll", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAreas(Areas record) {

        log.info("updateAreas");
        try {

            int updates = areasDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateAreas", e);
        }

        return false;
    }

}
