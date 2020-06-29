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
    private T mModel;
    public Repository(){
        mModel=createModel();
    }

    /**
     * 创建业务Model
     * @return
     */
    protected abstract T createModel();

    /**
     * 获取Model
     * @return
     */
    protected T getModel() {
        return mModel;
    }

//    /**
//     * 获取新的Model实例
//     * @param _model
//     * @return
//     */
//    protected <LocalModel extends IModel> LocalModel getModel(IModel _model) {
//        return (LocalModel) _model;
//    }
}
