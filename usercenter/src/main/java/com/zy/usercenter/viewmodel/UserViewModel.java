package com.zy.usercenter.viewmodel;

import com.zy.core.viewmodel.BaseViewModel;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.repository.UserRepository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/19
 */
public class UserViewModel extends BaseViewModel {
    public UserEntity userEntity=new UserEntity();

    public UserViewModel(LifecycleOwner _owner){
        super(_owner);
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository(_owner));
    }

    /**
     * 登录方法
     * @return
     */
    public LiveData<BaseRespEntity<UserEntity>> login(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.login(userEntity);
    }
}
