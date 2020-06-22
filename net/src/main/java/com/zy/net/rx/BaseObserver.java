package com.zy.net.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author:zhangyue
 * @date:2020/6/22
 */
public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
