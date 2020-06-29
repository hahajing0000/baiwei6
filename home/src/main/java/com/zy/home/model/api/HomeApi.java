package com.zy.home.model.api;

import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

/**
 * @author:zhangyue
 * @date:2020/6/29
 */
public interface HomeApi {

    /**
     * 获取Banner实体信息
     * @return
     */
    @GET("api/Img/getBannerImg")
    LiveData<BaseRespEntity<List<BannerEntity>>> getBanner();

    /**
     * 获取系统消息
     * @return
     */
    @GET("api/SystemMsg/getSystemMsg")
    LiveData<BaseRespEntity<List<SysMsgEntity>>> getSystemMsgs();

    /**
     * 获取新用户的推荐产品
     * @return
     */
    @GET("api/Product/getNewUserProcducts")
    LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct();

    /**
     * 获取推荐核心产品数据
     * @return
     */
    @GET("api/Product/getProcducts")
    LiveData<BaseRespEntity<List<ProductEntity>>> getProduct();
}
