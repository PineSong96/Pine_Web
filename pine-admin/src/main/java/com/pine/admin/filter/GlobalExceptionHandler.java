package com.pine.admin.filter;

import com.pine.common.dto.Result;
import com.pine.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常捕获 需要自定义一些异常然后解析
 * @Author: Pine
 * @Date: 2018/6/29 下午12:19
 * @Email:771190883@qq.com
 */
@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class, AuthorizationException.class})
    public Result defaultUnauthorizedException() {
        return Result.error("拒绝访问");
    }

    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(Exception e) throws Exception {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = ApiException.class)
    public Result defaultApiErrorHandler(ApiException e) {
        return Result.error(e.getCode(), e.getMessage());
    }


}
