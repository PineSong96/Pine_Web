package com.pine.admin.modules.base.service.impl;

import com.pine.admin.modules.base.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import org.springframework.transaction.annotation.Transactional;

import com.pine.admin.modules.base.dao.ProvincesDao;
import com.pine.admin.modules.base.service.ProvincesService;


@Service("provincesService")
@Slf4j
public class ProvincesServiceImpl implements ProvincesService {

    @Autowired
    private ProvincesDao provincesDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Provinces> getProvincesList(Provinces record) {

        log.info("getProvincesList");
        try {

            return provincesDao.queryAllProvinces(record);
        } catch (Exception e) {
            log.error("getProvincesList", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Provinces> getProvincesByPage(Page page, Provinces record) {

        log.info("getProvincesByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return provincesDao.queryProvincessByPage(parameter);
        } catch (Exception e) {
            log.error("getProvincesByPage", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Provinces getProvincesById(String id) {

        log.info("getProvincesById");
        try {

            return provincesDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getProvincesById", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Provinces getProvinces(Provinces record) {

        log.info("getProvinces");
        try {

            return provincesDao.queryProvinces(record);
        } catch (Exception e) {
            log.error("getProvinces", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createProvinces(Provinces record) {

        log.info("createProvinces");
        try {

            int updates = provincesDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createProvinces", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProvinces(String id) {

        log.info("deleteProvinces");
        try {

            int updates = provincesDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteProvinces", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProvincesAll(String ids) {

        log.info("deleteProvincesAll");
        try {

            String[] id = ids.split(";");
            int updates = provincesDao.deleteIds(id);

            if (id.length == updates) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteeProvincesAll", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProvinces(Provinces record) {

        log.info("updateProvinces");
        try {

            int updates = provincesDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateProvinces", e);
        }

        return false;
    }

}
