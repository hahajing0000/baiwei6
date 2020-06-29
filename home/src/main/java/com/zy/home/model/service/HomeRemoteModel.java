package com.zy.home.model.service;

import com.zy.core.model.IModel;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.api.HomeApi;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Home模块远程数据处理
 */
public class HomeRemoteModel implements IModel {

    private final HomeApi homeApi;

    public HomeRemoteModel(){
        homeApi = RetrofitFactory.getInstance().create(HomeApi.class);
    }
    /**
     * 获取Banner实体信息
     * @return
     */
    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner(){

        LiveData<BaseRespEntity<List<BannerEntity>>> banner = homeApi.getBanner();

        return banner;
    }

    /**
     * 获取系统消息
     * @return
     */
    public LiveData<BaseRespEntity<List<SysMsgEntity>>> getSystemMsgs(){
        LiveData<BaseRespEntity<List<SysMsgEntity>>> systemMsgs = homeApi.getSystemMsgs();
        return systemMsgs;
    }

    /**
     * 获取新用户的推荐产品
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct(){
        LiveData<BaseRespEntity<List<ProductEntity>>> newUserProduct = homeApi.getNewUserProduct();
        return newUserProduct;
    }

    /**
     * 获取推荐核心产品数据
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct(){
        LiveData<BaseRespEntity<List<ProductEntity>>> product = homeApi.getProduct();
        return product;
    }
}
