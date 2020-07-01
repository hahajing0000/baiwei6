package com.zy.finalce.model.api;

import com.zy.finalce.entity.FinalceEntity;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
public interface FinalceApi {
    /**
     * 根据理财产品的类型获取对应数据
     * @param type 理财产品的类型
     * @param currentPage 当前页码
     * @param pageSize 每页的数据数
     * @return
     */
    @GET("api/Product/getProcductsForType")//?type={type}&currentPage={currentPage}&pageSize={pageSize}
    LiveData<BaseRespEntity<List<FinalceEntity>>> getFinalceByType(@Query("type") int type, @Query("currentPage")int currentPage, @Query("pageSize") int pageSize);
}
