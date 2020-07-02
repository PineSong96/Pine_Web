package com.pine.common.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pine.common.utils.DateTimeTool;

import java.util.Date;

/**
 * <p>封装json返回类型。</p>
 *
 * @author 姓名 <br />
 * 更新履历 <br />
 * 日期 : 姓名: 更新内容<br />
 * @version 1.0
 */
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result {

    private boolean success;

    private Object data;

    private String error;

    private String code;

    private String timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result() {
    }

    public static Result success(boolean success, Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }


    public static Result error(String error) {

        Result result = new Result();
        result.setSuccess(false);
        result.setError(error);

        return result;
    }

    public static Result error(String code, String error) {

        Result result = new Result();
        result.setSuccess(false);
        result.setError(error);
        result.setCode(code);
        return result;
    }

    public boolean isSuccess() {

        return success;
    }


    public void setSuccess(boolean success) {

        this.success = success;
    }


    public Object getData() {

        return data;
    }


    public void setData(Object data) {

        this.data = data;
    }


    public String getError() {

        return error;
    }


    public void setError(String error) {

        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = DateTimeTool.dateTimeToYearMMddhhmmss(new Date());
    }
}
