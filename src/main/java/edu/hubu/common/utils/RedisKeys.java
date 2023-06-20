/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package edu.hubu.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RedisKeys {

    /**
     * 测试二维码
     */
    public static final String EXAM_QRCODE = "exam:qr:";

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }
}
