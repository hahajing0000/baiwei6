package com.zy.usercenter;

import com.zy.common.app.BaseAppcation;
import com.zy.storage.core.StorageManager;
import com.zy.storage.core.StorageType;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public class UserCenterApp extends BaseAppcation {
    @Override
    protected void initOtherConfig() {
        //初始化存储位置为SP
        StorageManager.getInstance().init(StorageType.SP);
    }
}
