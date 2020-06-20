package com.zy.net;

import android.os.Build;
import android.text.TextUtils;

import com.zy.common.utils.LogUtils;
import com.zy.net.api.TokenApi;
import com.zy.net.common.Config;
import com.zy.net.protocol.TokenRespEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:zhangyue
 * @date:2020/6/20
 * retrofit 工厂类
 */
public class RetrofitFactory {
    private final String TAG=RetrofitFactory.class.getSimpleName();
    private volatile static RetrofitFactory instance=null;
    private Retrofit retrofit;

    private RetrofitFactory(){
        initRetrofit();
    }

    public static RetrofitFactory getInstance(){
        if (null==instance){
            synchronized (RetrofitFactory.class){
                if (null==instance){
                    instance=new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 创建OkHttp客户端
     * @return
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(Config.TIMEOUT,TimeUnit.SECONDS)
                .addNetworkInterceptor(createNetworkInterceptor())
                .addInterceptor(tokenInterceptor())
                .addInterceptor(headerParamsInterceptor())
                .build();
        return client;
    }

    /**
     * 头信息拦截器
     * @return
     */
    private Interceptor headerParamsInterceptor() {
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("v0", Build.MANUFACTURER)
                        .addHeader("v1",Build.MODEL)
                        .addHeader("v2",Build.TYPE)
                        .addHeader("v3",""+Build.VERSION.SDK_INT)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    /**
     * Token拦截器
     * @return
     */
    private Interceptor tokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)){
                    String token=requestToken();
                    if (TextUtils.isEmpty(token)){
                        LogUtils.INSTANCE.e(TAG,"Error : token is null...");
                        return response;
                    }
                    //Request newRequest=chain.request();
                    Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer " + token);

                    Request newRequest=newBuilder.build();
                    return chain.proceed(newRequest);
                }
                return response;
            }
        };

        return interceptor;
    }

    /**
     * 判断HTTP CODE 是否401 —— TOKEN失效
     * @param response
     * @return
     */
    private boolean checkHttpCode401(Response response) {
        if (null==response){
            return false;
        }

        if (response.code()==401){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 获取Token的同步网络请求
     * @return
     */
    private String requestToken() {

        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result!=null&&result.body()!=null){
                return  result.body().getAccess_token();
            }
        } catch (IOException e) {
            LogUtils.INSTANCE.e(TAG,e.getMessage());
        }
        return "";
    }

    /**
     * 网络请求的拦截器
     * @return
     */
    private Interceptor createNetworkInterceptor() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 创建服务器请求
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
