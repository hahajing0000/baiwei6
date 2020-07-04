package com.zy.router.interceptor;

import android.content.Context;
import android.os.IInterface;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.zy.router.RouterPath;
import com.zy.storage.utils.SharePreferenceUtils;

/**
 * @author:zhangyue
 * @date:2020/7/4
 */
@Interceptor(name = "LogInterceptor",priority = 1)
public class LogInterceptor implements IInterceptor {
    private Context mContext;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        if (path.equals(RouterPath.USERCENTER_LOGIN)){
            callback.onContinue(postcard);
        }
        //通过path判断是否前往理财页面
        if (path.equals(RouterPath.Finalce_BUY)){
            //判断如果未登陆  则进行拦截 如果已登录放行
            if (isLogin()){
                callback.onContinue(postcard);
            }else{
                callback.onInterrupt(null);
            }
        }
    }

    /**
     * 判断是否登录 true 已登录 false 未登录
     * @return
     */
    private boolean isLogin() {
        //从sp中查找登录的相关数据 如果有就表示已经登陆 没有就是没登录
        String state = (String) SharePreferenceUtils.get(mContext, "login", "");
        if (TextUtils.isEmpty(state)){
            return false;
        }
        return true;
    }

    @Override
    public void init(Context context) {
        mContext=context;
    }
}
