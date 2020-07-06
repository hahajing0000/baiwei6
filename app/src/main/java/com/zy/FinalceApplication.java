package com.zy;

import com.zy.common.app.BaseAppcation;
import com.zy.router.RouterManager;

import androidx.multidex.MultiDex;

/**
 * @author:zhangyue
 * @date:2020/6/28
 */
public class FinalceApplication extends BaseAppcation {
    @Override
    protected void initOtherConfig() {
        RouterManager.getInstance().init(this,true);
        MultiDex.install(this);

    }
}
