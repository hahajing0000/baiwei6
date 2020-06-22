package com.zy.usercenter.model;

import android.os.Looper;

import com.zy.common.utils.LogUtils;
import com.zy.core.model.IModel;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.model.api.UserApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public LiveData<BaseRespEntity<UserEntity>> login(UserEntity userEntity){
        LogUtils.INSTANCE.d(TAG,"userEntity: username-"+userEntity.getUsername()+" pwd-"+userEntity.getPwd());
//        MutableLiveData<Boolean> result=new MutableLiveData<>();
//        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
//            result.setValue(false);
//
//        }
//        else{
//            result.postValue(false);
//        }
//        return result;
        final MutableLiveData<BaseRespEntity<UserEntity>> result=new MutableLiveData<>();
        UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
        Call<BaseRespEntity<UserEntity>> call = userApi.login(userEntity);
        call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
            @Override
            public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
                result.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
                result.postValue(null);
                LogUtils.INSTANCE.e(TAG,t);
            }
        });

        return result;
    }
}
