package com.zy.usercenter.model;

import com.zy.core.model.IModel;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.rx.BaseObservable;
import com.zy.net.rx.BaseObserver;
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

        final MutableLiveData<BaseRespEntity<UserEntity>> result=new MutableLiveData<>();
        Flowable.create(new FlowableOnSubscribe<BaseRespEntity<UserEntity>>() {
            @Override
            public void subscribe(final FlowableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
                UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
                Call<BaseRespEntity<UserEntity>> call = userApi.login(userEntity);
                call.enqueue(new Callback<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onResponse(Call<BaseRespEntity<UserEntity>> call, Response<BaseRespEntity<UserEntity>> response) {
                        if (response.body().getCode() == -1) {
                            emitter.onError(new RuntimeException("用户登录失败"));
                            return;
                        }

                        emitter.onNext(response.body());
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(Call<BaseRespEntity<UserEntity>> call, Throwable t) {
                        emitter.onError(t);
                    }
                });
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                        result.postValue(userEntityBaseRespEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        result.postValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Observable<BaseRespEntity<UserEntity>> observable = Observable.create(new ObservableOnSubscribe<BaseRespEntity<UserEntity>>() {
//            @Override
//            public void subscribe(final ObservableEmitter<BaseRespEntity<UserEntity>> emitter) throws Exception {
//
//
//            }
//        });

//        BaseObservable.doObservable(observable,new BaseObserver<BaseRespEntity<UserEntity>>(){
//            @Override
//            public void onNext(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
//                super.onNext(userEntityBaseRespEntity);
//                result.postValue(userEntityBaseRespEntity);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                result.postValue(null);
//            }
//        });
        return result;
    }
}
