package com.zy.finalce.model.service;

import com.zy.core.model.IModel;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.model.api.FinalceApi;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.Path;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
public class FinalceRemoteService implements IModel {
    /**
     * 根据理财产品的类型获取对应数据
     * @param type 理财产品的类型
     * @param currentPage 当前页码
     * @param pageSize 每页的数据数
     * @return
     */
    public LiveData<BaseRespEntity<List<FinalceEntity>>> getFinalceByType(int type, int currentPage,  int pageSize){
        FinalceApi api = RetrofitFactory.getInstance().create(FinalceApi.class);
        LiveData<BaseRespEntity<List<FinalceEntity>>> result = api.getFinalceByType(type, currentPage, pageSize);
        return result;
    }
}
