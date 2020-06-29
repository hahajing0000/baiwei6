package com.zy.home.model.service;

import com.zy.core.model.IModel;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.database.HomeDBHelper;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Home 模块本地数据处理 （sqlite） —— ROOM
 */
public class HomeLocalModel implements IModel {
    /**
     * 获取Banner实体信息
     * @return
     */
    public LiveData<List<BannerEntity>> getBanner(){

        LiveData<List<BannerEntity>> banner = HomeDBHelper
                .getInstance()
                .getDB()
                .homeDao()
                .getBannerAll();

        return banner;
    }

    /**
     * 添加Banner数据
     * @param banners
     */
    public  void insertBannerAll(List<BannerEntity> banners){
        HomeDBHelper.getInstance().getDB()
                .homeDao().insertBannerAll(banners);
    }

    /**
     * 获取系统消息
     * @return
     */
    public LiveData<List<SysMsgEntity>> getSystemMsgs(){
        LiveData<List<SysMsgEntity>> systemMsgs = HomeDBHelper.getInstance()
                .getDB().homeDao().getSysMsgAll();
        return systemMsgs;
    }

    /**
     * 添加系统消息
     * @param sysmsgs
     */
    public  void insertSysMsgAll(List<SysMsgEntity> sysmsgs){
        HomeDBHelper.getInstance().getDB()
                .homeDao().insertSysMsgAll(sysmsgs);
    }

    /**
     * 获取新用户的推荐产品
     * @return
     */
    public LiveData<List<ProductEntity>> getNewUserProduct(){
        LiveData<List<ProductEntity>> newUserProduct = HomeDBHelper.getInstance()
                .getDB().homeDao().getNewUserProductAll();
        return newUserProduct;
    }

    /**
     * 获取推荐核心产品数据
     * @return
     */
    public LiveData<List<ProductEntity>> getProduct(){
        LiveData<List<ProductEntity>> product = HomeDBHelper.getInstance()
                .getDB().homeDao().getProductAll();
        return product;
    }

    /**
     * 添加金融产品方法
     * @param products
     */
    public  void insertProductAll(List<ProductEntity> products){
        HomeDBHelper.getInstance().getDB().homeDao().insertProductAll(products);
    }
}
