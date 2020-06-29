package com.zy.core.viewmodel;

import com.zy.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

/**
 * @author:zhangyue
 * @date:2020/6/19
 */
public abstract class BaseViewModel extends ViewModel implements LifecycleObserver {
    protected LifecycleOwner owner;
    protected Map<String, Repository> repositoryMap;
    public BaseViewModel(LifecycleOwner _owner){
        repositoryMap=new HashMap<>();
        owner=_owner;
    }



    /**
     * 注册数据仓库
     * @param key 数据仓库的标识
     * @param repository 数据仓库的实例
     */
    protected void registerRepository(String key,Repository repository){
        if (repositoryMap.containsKey(key)){
            return;
        }
        repositoryMap.put(key,repository);
    }

    /**
     * 注销数据仓库
     * @param key 数据仓库的标识
     */
    protected void unRegisterRepository(String key){
        if (repositoryMap.containsKey(key)){
            repositoryMap.remove(key);
        }
    }

    /**
     * 根据key获取具体的数据仓库
     * @param key 数据仓库的标识
     * @param <SubRepository> 具体的数据仓库
     * @return
     */
    protected <SubRepository extends Repository> SubRepository getRepository(String key){
        if (repositoryMap.containsKey(key)){
            return (SubRepository)repositoryMap.get(key);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnectOwner(){
        repositoryMap.clear();
        repositoryMap=null;
    }
}
