package com.zy.net.retrofit.calladapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public class DefaultCallAdapter<R> implements CallAdapter<R,Object> {
    Type type;
    public DefaultCallAdapter(Type _t){
        type=_t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
