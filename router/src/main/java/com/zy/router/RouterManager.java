package com.zy.router;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @author:zhangyue
 * @date:2020/7/3
 */
public class RouterManager {
    private volatile static RouterManager instance=null;
    private RouterManager(){}
    public static RouterManager getInstance(){
        if (null==instance){
            synchronized (RouterManager.class){
                if (null==instance){
                    instance=new RouterManager();
                }
            }
        }
        return instance;
    }

    /**
     * ARouter初始化
     * @param application
     * @param isDebug
     */
    public void init(Application application, boolean isDebug){
        if (isDebug){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }

    /**
     * 路由跳转方法
     * @param path
     */
    public void route(String path){
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 路由跳转方法  （可携带参数）
     * @param path
     * @param params
     */
    public void route(String path, Map<String,Object> params){
        Postcard build = ARouter.getInstance().build(path);
        if (params!=null&&params.size()>0){
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> set:entries){
                Object value = set.getValue();
                if (value instanceof Boolean){
                    build.withBoolean(set.getKey(), (Boolean) value);
                }else if (value instanceof Bundle){
                    build.withBundle(set.getKey(), (Bundle) value);
                }else if (value instanceof Integer){
                    build.withInt(set.getKey(), (Integer) value);
                }else if (value instanceof Double){
                    build.withDouble(set.getKey(), (Double) value);
                }else if (value instanceof Float){
                    build.withString(set.getKey(), (String) value);
                }else if (value instanceof Parcelable){
                    build.withParcelable(set.getKey(), (Parcelable) value);
                }else if (value instanceof Serializable){
                    build.withSerializable(set.getKey(), (Serializable) value);
                }
            }
        }
        build.navigation();
    }

    /**
     * 普通Activity路由
     * @param context
     * @param intent
     */
    public void route(Context context, Intent intent){
        context.startActivity(intent);
    }


}
