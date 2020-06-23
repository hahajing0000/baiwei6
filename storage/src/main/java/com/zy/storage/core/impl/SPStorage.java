package com.zy.storage.core.impl;

import com.zy.common.app.BaseAppcation;
import com.zy.common.utils.LogUtils;
import com.zy.storage.core.IStorage;
import com.zy.storage.utils.SharePreferenceUtils;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public class SPStorage implements IStorage {
    private final String TAG=SPStorage.class.getSimpleName();
    @Override
    public <T> boolean save(String key, T value) {
        try {
            SharePreferenceUtils.put(BaseAppcation.getAppContext(),key,value);
        }
        catch (Exception ex){
            LogUtils.INSTANCE.e(TAG,ex);
            return false;
        }

        return true;
    }

    @Override
    public <T> T get(String key) {
        T result = (T) SharePreferenceUtils.get(BaseAppcation.getAppContext(), key, "");
        return result;
    }
}
