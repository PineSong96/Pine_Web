package com.pine.admin.modules.base.binder;

import com.pine.common.dto.Result;
import com.pine.common.exception.ApiException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;

public class BaseController {

    @ExceptionHandler(value = {UnauthorizedException.class, AuthorizationException.class})
    public Result defaultUnauthorizedException() throws Exception {

        return Result.error("拒绝访问");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String value) {

                setValue(new Date(Long.valueOf(value)));
            }
        });
    }
}
