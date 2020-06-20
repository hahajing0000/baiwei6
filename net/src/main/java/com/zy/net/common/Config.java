package com.zy.net.common;

import android.os.Build;

import com.zy.net.BuildConfig;

/**
 * @author:zhangyue
 * @date:2020/6/20
 * 程序相关配置 —— 网络部分
 */
public class Config {
    /**
     * 验证码 用于向后台请求Token使用
     */
    public final static String AUTH_CODE="651851771c11d419413f1b91b717e16312e18f1d71d91d01";
    /**
     * 网络请求超时时长
     */
    public final static int TIMEOUT=10;

    /**
     * 网络请求的基础地址
     */
    public final static String BASE_URL= BuildConfig.baseUrl;
}
