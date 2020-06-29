package com.zy.common.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * 网络状态工具类  2G/3G/4G/5G  WIFI
 */
public class NetStateUtils {
    /**
     * 网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context){
        if (context!=null){
            ConnectivityManager connectServerManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectServerManager.getActiveNetworkInfo();
            if (activeNetworkInfo==null){
                return false;
            }

            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 网络是否可以正常连接
     * @return
     */
    public static boolean isConnected(){
        URL url;
        InputStream stream = null;
        try {
            url= new URL("https://www.baidu.com");
            stream= url.openStream();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

}
