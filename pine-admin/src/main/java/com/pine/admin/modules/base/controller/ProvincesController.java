package com.pine.admin.modules.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.common.dto.Page;
import com.pine.admin.modules.base.binder.BaseController;
import com.pine.common.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pine.admin.modules.base.entity.Provinces;
import com.pine.admin.modules.base.service.ProvincesService;


/**
 * 省份信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:57
 */
@Api(value = "省份信息接口", tags = {"省份信息接口" })
@RestController
@RequestMapping("base/Provinces")
public class ProvincesController extends BaseController {

    @Autowired
    private ProvincesService provincesService;


    /**
     * <p>获取全部记录。</p>
     */
    @ApiOperation(value = "获取所有省份信息数据", notes = "可以根据条件获取")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(Provinces record) {

        List<Provinces> pp = provincesService.getProvincesList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @ApiOperation(value = "获取省份信息数据", notes = "根据id来获取省份信息详细信息")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result getProvincesEntity(@PathVariable("id") String id) {

        Provinces data = provincesService.getProvincesById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @ApiOperation(value = "获取省份信息数据", notes = "根据条件获取")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(Provinces record) {

        Provinces data = provincesService.getProvinces(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @ApiOperation(value = "分页获取省份信息数据", notes = "根据条件获取")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, Provinces record) {

        Map<String, Object> result = new HashMap<>();

        List<Provinces> pp = provincesService.getProvincesByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @ApiOperation(value = "新增一条省份信息数据", notes = "新增一条省份信息数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(Provinces record) {

        boolean num = provincesService.createProvinces(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @ApiOperation(value = "更新一条省份信息数据", notes = "更新一条省份信息数据")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateProvinces(Provinces record) {

        boolean num = provincesService.updateProvinces(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除一条省份信息数据", notes = "根据Id一条省份信息数据")
    public Result deleteProvinces(@PathVariable("id") String id) {

        boolean num = provincesService.deleteProvinces(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>批量删除。</p>
     */
//    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
//    @ApiOperation(value = "删除多条省份信息数据", notes = "删除id的集合多个用英文逗号分隔")
//    public Result delProvincesAll(@RequestParam(required = true, value = "ids") String ids ) {
//
//        boolean num = provincesService.deleteProvincesAll(ids);
//        if (num) {
//            return Result.success(true, "删除成功");
//        }
//        return Result.error( "删除异常");
//    }

}
