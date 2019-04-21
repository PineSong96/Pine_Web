package com.pine.admin.modules.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import com.pine.common.dto.Page;
import org.springframework.transaction.annotation.Transactional;
import com.pine.admin.modules.base.dao.CitiesDao;
import com.pine.admin.modules.base.entity.Cities;
import com.pine.admin.modules.base.service.CitiesService;


@Service("citiesService")
@Slf4j
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesDao citiesDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Cities> getCitiesList(Cities record) {

        log.info("getCitiesList");
        try {

            return citiesDao.queryAllCities(record);
        } catch (Exception e) {
            log.error("getCitiesList", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Cities> getCitiesByPage(Page page, Cities record) {

        log.info("getCitiesByPage");
        try {

            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("record", record);
            parameter.put("page", page);

            return citiesDao.queryCitiessByPage(parameter);
        } catch (Exception e) {
            log.error("getCitiesByPage", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Cities getCitiesById(String id) {

        log.info("getCitiesById");
        try {

            return citiesDao.selectByPrimaryKey(Integer.valueOf(id));
        } catch (Exception e) {
            log.error("getCitiesById", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Cities getCities(Cities record) {

        log.info("getCities");
        try {

            return citiesDao.queryCities(record);
        } catch (Exception e) {
            log.error("getCities", e);
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createCities(Cities record) {

        log.info("createCities");
        try {

            int updates = citiesDao.insertSelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("createCities", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCities(String id) {

        log.info("deleteCities");
        try {

            int updates = citiesDao.delete(Integer.valueOf(id));

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteCities", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCitiesAll(String ids) {

        log.info("deleteeCitiesAll");
        try {

            String[] id = ids.split(";");
            int updates = citiesDao.deleteIds(id);

            if (id.length == updates) {
                return true;
            }
        } catch (Exception e) {
            log.error("deleteeCitiesAll", e);
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCities(Cities record) {

        log.info("updateCities");
        try {

            int updates = citiesDao.updateByPrimaryKeySelective(record);

            if (updates > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("updateCities", e);
        }

        return false;
    }

}
