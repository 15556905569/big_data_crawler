package com.jxl.jcrawler.util.common;

import java.nio.charset.Charset;

/**
 * Created by echo on 2016/11/7.
 */
public class Consts {
    public static final Charset UTF8 = Charset.forName("utf8");
    public static final String TIMEOUT = "timeout";

    public static final String PUBLIC_CONFIG = "/config/public_config.properties";
    public static final String PHONE_MSG_CONFIG = "/config/phone_msg.properties";
    public static final String SHORT_URL_CONFIG = "/config/short_url.properties";
    public static final String LONG_URL_CONFIG = "/config/sms_templates.properties";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    /**
     * 默认页码
     */
    public static final Integer DEFAULT_PAGE_NUM = 1;

    /**
     * 单次最大发送手机号数 暂定为 100
     */
    public static final Integer MAX_SEND_NUM_OF_PHONES = 100;

    /**
     * 线程池数量
     */
    public static final Integer MAX_THREAD_NUM = 5;
    /**
     * 最大持续发送时间,单位小时
     */
    public static final Integer MAX_SEND_TIME = 3;
    //    /**
//     * 初始化
//     */
    private static PropertiesUtil propertiesUtil = new PropertiesUtil(PUBLIC_CONFIG);

    //    /**
//     * 供应商最高优先级
//     */
//    public static final Integer MAX_PRIORITY = propertiesUtil.getIntegerValue("MAX_PRIORITY");
//
//    /**
//     * 构造
//     */
//    private Consts() {
//        throw new IllegalAccessError("Consts class");
//    }
//
    public static synchronized void init() {
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil(PUBLIC_CONFIG);
        }
    }

    public static String getValue(String key) {
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil(PUBLIC_CONFIG);
        }
        return propertiesUtil.getValue(key);
    }

}
