package com.zy.net.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:zhangyue
 * @date:2020/6/22
 */
public class BaseObservable {
    public static <T> void doObservable(Observable<T> tObservable,BaseObserver<T> observer){
        tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
