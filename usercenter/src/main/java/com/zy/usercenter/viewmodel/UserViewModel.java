package com.zy.usercenter.viewmodel;

import com.zy.core.viewmodel.BaseViewModel;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.repository.UserRepository;

import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/19
 */
public class UserViewModel extends BaseViewModel {
    public UserEntity userEntity=new UserEntity();

    public UserViewModel(){
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository());
    }

    /**
     * 登录方法
     * @return
     */
    public LiveData<Boolean> login(){
        UserRepository userRepository=getRepository(UserRepository.class.getSimpleName());
        return userRepository.login(userEntity);
    }
}
