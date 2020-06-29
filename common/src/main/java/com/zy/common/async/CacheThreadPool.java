package com.zy.common.async;

import java.util.concurrent.Executors;

/**
 * @author:zhangyue
 * @date:2020/6/29
 */
public class CacheThreadPool {
    private static CacheThreadPool instance=new CacheThreadPool();
    private CacheThreadPool(){}
    public static CacheThreadPool getInstance(){
        return instance;
    }

    public void execute(Runnable runnable){
        Executors.newCachedThreadPool().submit(runnable);
    }
}
