package com.zy.usercenter.repository;

import com.zy.common.utils.LogUtils;
import com.zy.core.model.IModel;
import com.zy.core.repository.Repository;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.model.UserModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author:zhangyue
 * @date:2020/6/19
 * 用户模块的数据仓库层
 */
public class UserRepository extends Repository<UserModel> {
    @Override
    protected UserModel createModel() {
        return new UserModel();
    }

    /**
     * 登录方法
     * @param userEntity
     * @return
     */
    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity userEntity){
        /**
         * 无网络
         * 可以选择加载本地数据（sqlite  sp  file  lrucache）
         */
        return mModel.login(userEntity);
    }
}
