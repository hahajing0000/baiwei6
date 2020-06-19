package com.zy.core.repository;

import com.zy.core.model.IModel;

/**
 * @author:zhangyue
 * @date:2020/6/19
 * 数据仓库的基类
 */
public abstract class Repository<T extends IModel> {
    /**
     * 业务Model
     */
    protected T mModel;
    public Repository(){
        mModel=createModel();
    }

    /**
     * 创建业务Model
     * @return
     */
    protected abstract T createModel();
}
