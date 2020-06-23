package com.zy.net.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:zhangyue
 * @date:2020/6/23
 */
public class BaseFlowable {
    public static <T> void doObservable(Flowable<T> flowable, BaseSubscriber<T> subscriber){
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
