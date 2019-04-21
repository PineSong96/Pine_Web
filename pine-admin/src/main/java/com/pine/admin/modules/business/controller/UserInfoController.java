package com.pine.admin.modules.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pine.common.dto.Page;
import com.pine.admin.modules.base.binder.BaseController;
import com.pine.common.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.pine.admin.modules.business.entity.UserInfo;
import com.pine.admin.modules.business.service.UserInfoService;


/**
 * 用户信息
 *
 * @author Pine
 * @email 771190883@qq.com
 * @date 2019-03-31 21:13:10
 */
@Api(value = "用户信息接口", tags = {"用户信息接口" })
@RestController
@RequestMapping("business/UserInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;


    /**
     * <p>获取全部记录。</p>
     */
    @ApiOperation(value = "获取所有用户信息数据", notes = "可以根据条件获取")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(UserInfo record) {

        List<UserInfo> pp = userInfoService.getAllList(record);
        return Result.success(true, pp);
    }


    /**
     * <p>根据Id。</p>
     */
    @ApiOperation(value = "获取用户信息数据", notes = "根据id来获取用户信息详细信息")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result getUserInfoEntity(@PathVariable("id") String id) {

        UserInfo data = userInfoService.getById(id);
        return Result.success(true, data);
    }

    /**
     * <p>根据条件获取。</p>
     */
    @ApiOperation(value = "获取用户信息数据", notes = "根据条件获取")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public Result getOne(UserInfo record) {

        UserInfo data = userInfoService.getOne(record);
        return Result.success(true, data);
    }

    /**
     * <p>分页查询。</p>
     */
    @ApiOperation(value = "分页获取用户信息数据", notes = "根据条件获取")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(Page page, UserInfo record) {

        Map<String, Object> result = new HashMap<>();

        List<UserInfo> pp = userInfoService.getByPage(page, record);

        result.put("page", page);
        result.put("pp", pp);
        return Result.success(true, result);
    }

    /**
     * <p>保存。</p>
     */
    @ApiOperation(value = "新增一条用户信息数据", notes = "新增一条用户信息数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(UserInfo record) {

        boolean num = userInfoService.create(record);
        if (num) {
            return Result.success(true, record.getId());
        }
        return Result.error("保存异常");
    }

    /**
     * <p>更新信息。</p>
     */
    @ApiOperation(value = "更新一条用户信息数据", notes = "更新一条用户信息数据")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateUserInfo(UserInfo record) {

        boolean num = userInfoService.update(record);
        if (num) {
            return Result.success(true, "保存成功");
        }
        return Result.error("保存异常");
    }

    /**
     * <p>删除。</p>
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除一条用户信息数据", notes = "根据Id一条用户信息数据")
    @RequiresPermissions("userInfo::delete::id")
    public Result deleteUserInfo(@PathVariable("id") String id) {

        boolean num = userInfoService.delete(id);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

    /**
     * <p>批量删除。</p>
     */
    @RequestMapping(value = "/delAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除多条用户信息数据", notes = "删除id的集合多个用英文逗号分隔")
    public Result delUserInfoAll(@RequestParam(required = true, value = "ids") String ids) {

        boolean num = userInfoService.deltetByIds(ids);
        if (num) {
            return Result.success(true, "删除成功");
        }
        return Result.error("删除异常");
    }

}
