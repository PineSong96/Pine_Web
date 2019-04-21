/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.pine.common.utils;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /** 数据权限过滤 */
    public static final String SQL_FILTER = "sql_filter";
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";
    /**
     *  兑换码生成位数
     */
    public static final Integer EXCHANGE_CODE_SIZE = 15;

    /**
     *  订单 支付方式 微信
     */
    public static final Integer ORDER_PAY_WEI = 0;

    /**
     *  订单 支付方式 微信
     */
    public static final Integer ORDER_PAY_CODE = 1;

    /**
     *  订单 支付状态 已经支付
     */
    public static final Integer ORDER_PAY_ALREADY = 0;

    /**
     *  订单 支付状态 未支付
     */
    public static final Integer ORDER_PAY_YET = 1;


    /**
     *  订单 状态 0待支付
     */
    public static final Integer ORDER_STATUS_TO_PAY = 0;
    /**
     *  订单 状态 1代发货
     */
    public static final Integer ORDER_STATUS_TO_SHIP = 1;
    /**
     *  订单 状态 2代收货
     */
    public static final Integer ORDER_STATUS_TO_RECEIPT = 2;
    /**
     *  订单 状态 3已签收
     */
    public static final Integer ORDER_STATUS_TO_SIGNING = 3;
    /**
     * 通用密码信息
     */
    public static final String USER_PASSWORD = "KOI";
    public static final String USER_SALT = "KOI";
    /**
     * 默认收货地址  0 不是 1是的
     */
    public static final Integer DEFAULT_RECEIPT_YES = 1;
    public static final Integer DEFAULT_RECEIPT_NO = 0;
    /**
     * 用户类型
     */
    public static final Integer USER_ADMIN = 1;
    public static final Integer USER_WEIXIN = 0;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
