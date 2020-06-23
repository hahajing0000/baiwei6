package com.zy.usercenter.model;

import com.zy.core.model.IModel;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.rx.BaseFlowable;
import com.zy.net.rx.BaseObservable;
import com.zy.net.rx.BaseObserver;
import com.zy.net.rx.BaseSubscriber;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.model.api.UserApi;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    Disposable disposable=null;
    /**
     * 登录方法
     * @param userEntity
     * @return
     */
    public LiveData<BaseRespEntity<UserEntity>> login(final UserEntity userEntity){
        final UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
        LiveData<BaseRespEntity<UserEntity>> result = userApi.login(userEntity);
        return result;
    }
}
