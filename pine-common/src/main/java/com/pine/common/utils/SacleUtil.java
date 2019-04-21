package com.pine.common.utils;

import io.swagger.models.auth.In;

/**
 * @Author: Pine
 * @Date: 2018/7/18 下午6:10
 * @Email:771190883@qq.com
 */
public class SacleUtil {

    public static Integer getSacleId(Integer i) {

        if (i >= 0 & i < 20) {
            return 1;
        }
        if (i >= 20 & i < 100) {
            return 2;
        }
        if (i >= 100 & i < 500) {
            return 3;
        }
        if (i >= 500 & i < 1000) {
            return 4;
        }
        if (i >= 1000 & i < 10000) {
            return 5;
        }
        if (i >= 10000) {
            return 6;
        }
        return 1;
    }
}
