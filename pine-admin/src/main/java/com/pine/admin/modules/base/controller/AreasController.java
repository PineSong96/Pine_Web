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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pine.admin.modules.base.entity.Areas;
import com.pine.admin.modules.base.service.AreasService;


/**
 * 区县信息表
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-30 15:41:56
 */
@Api(value = "区县信息表接口", tags = {"区县信息表接口" })
@RestController
@RequestMapping("base/Areas")
public class AreasController extends BaseController {

    @Autowired
    private AreasService areasService;


    /**
     * <p>获取全部记录。</p>
     */
    @ApiOperation(value = "获取所有区县信息表数据", notes = "可以根据条件获取")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(Areas record) {

        List<Areas> pp = areasService.getAreasList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @ApiOperation(value = "获取区县信息表数据", notes = "根据id来获取区县信息表详细信息")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result getAreasEntity(@PathVariable("id") String id) {

        Areas data = areasService.getAreasById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @ApiOperation(value = "获取区县信息表数据", notes = "根据条件获取")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(Areas record) {

        Areas data = areasService.getAreas(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @ApiOperation(value = "分页获取区县信息表数据", notes = "根据条件获取")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, Areas record) {

        Map<String, Object> result = new HashMap<>();

        List<Areas> pp = areasService.getAreasByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @ApiOperation(value = "新增一条区县信息表数据", notes = "新增一条区县信息表数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(Areas record) {

        boolean num = areasService.createAreas(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @ApiOperation(value = "更新一条区县信息表数据", notes = "更新一条区县信息表数据")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateAreas(Areas record) {

        boolean num = areasService.updateAreas(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除一条区县信息表数据", notes = "根据Id一条区县信息表数据")
    public Result deleteAreas(@PathVariable("id") String id) {

        boolean num = areasService.deleteAreas(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>批量删除。</p>
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除多条区县信息表数据", notes = "删除id的集合多个用英文逗号分隔")
    public Result delAreasAll(@RequestParam(required = true, value = "ids") String ids) {

        boolean num = areasService.deleteAreasAll(ids);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

}
