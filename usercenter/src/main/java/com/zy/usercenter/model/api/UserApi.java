package com.zy.usercenter.model.api;

import com.zy.net.protocol.BaseRespEntity;
import com.zy.usercenter.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author:zhangyue
 * @date:2020/6/22
 */
public interface UserApi {
//    @Headers({Config.NewUrlHeaderKey+":"+ Config.NewUrlHeaderValue})
//    @GET("/login")
//    Call<TokenRespEntity> getTest();

    @POST("api/User/login")
    Call<BaseRespEntity<UserEntity>> login(@Body UserEntity userEntity);
}
