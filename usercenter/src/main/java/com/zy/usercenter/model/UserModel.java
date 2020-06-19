package com.zy.usercenter.model;

import android.os.Looper;

import com.zy.common.utils.LogUtils;
import com.zy.core.model.IModel;
import com.zy.usercenter.entity.UserEntity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author:zhangyue
 * @date:2020/6/19
 * Model层
 */
public class UserModel implements IModel {
    private final String TAG=UserModel.class.getSimpleName();
    /**
     * 登录方法
     * @param userEntity
     * @return
     */
    public LiveData<Boolean> login(UserEntity userEntity){
        LogUtils.INSTANCE.d(TAG,"userEntity: username-"+userEntity.getUsername()+" pwd-"+userEntity.getPwd());
        MutableLiveData<Boolean> result=new MutableLiveData<>();
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            result.setValue(false);

        }
        else{
            result.postValue(false);
        }
        return result;
    }
}
