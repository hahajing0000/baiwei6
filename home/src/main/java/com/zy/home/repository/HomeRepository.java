package com.zy.home.repository;

import com.zy.common.app.BaseAppcation;
import com.zy.common.net.NetStateUtils;
import com.zy.core.model.IModel;
import com.zy.core.repository.Repository;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.database.HomeDBHelper;
import com.zy.home.model.service.HomeLocalModel;
import com.zy.home.model.service.HomeRemoteModel;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.rx.BaseObservable;
import com.zy.net.rx.BaseObserver;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Home模块的数据仓库
 */
public class HomeRepository extends Repository<HomeRemoteModel> {

    /**
     * Home模块本地Model
     */
    private HomeLocalModel homeLocalModel=null;

    public HomeRepository(){
        homeLocalModel = new HomeLocalModel();
    }

    @Override
    protected HomeRemoteModel createModel() {
        return new HomeRemoteModel();
    }

    /**
     * 获取Banner实体信息
     * @return
     */
    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner(){
        /**
         * 先判断当前的网络状态是否可用
         * 如果可以使用远程数据
         * 如果不可用使用本地数据
         */
        LiveData<BaseRespEntity<List<BannerEntity>>> banner = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            banner=getModel().getBanner();
            final LiveData<BaseRespEntity<List<BannerEntity>>> finalBanner = banner;
            Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                    //将网络数据结果存储到本地sqlite数据库
                    HomeDBHelper.getInstance().getDB().homeDao().insertBannerAll(finalBanner.getValue().getData());
                }
            });
            BaseObservable.doObservable(observable,new BaseObserver<Boolean>(){
                @Override
                public void onNext(Boolean aBoolean) {
                    super.onNext(aBoolean);
                }
            });

        }else{
            //从本地sqlite数据库提取缓存的数据
            LiveData<List<BannerEntity>> banner1 = homeLocalModel.getBanner();
            MutableLiveData<BaseRespEntity<List<BannerEntity>>> data= new MutableLiveData<>();
            data.setValue(new BaseRespEntity<List<BannerEntity>>(banner1.getValue()));
            return data;

        }

        return banner;
    }

    /**
     * 获取系统消息
     * @return
     */
    public LiveData<BaseRespEntity<List<SysMsgEntity>>> getSystemMsgs(){
        LiveData<BaseRespEntity<List<SysMsgEntity>>> systemMsgs = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            systemMsgs=getModel().getSystemMsgs();
            //将网络数据结果存储到本地sqlite数据库
            HomeDBHelper.getInstance().getDB().homeDao().insertSysMsgAll(systemMsgs.getValue().getData());
        }else{
            //从本地sqlite数据库提取缓存的数据
            LiveData<List<SysMsgEntity>> liveData = homeLocalModel.getSystemMsgs();
            MutableLiveData<BaseRespEntity<List<SysMsgEntity>>> data= new MutableLiveData<>();
            data.setValue(new BaseRespEntity<List<SysMsgEntity>>(liveData.getValue()));
            return data;
        }

        return systemMsgs;
    }

    /**
     * 获取新用户的推荐产品
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct(){

        LiveData<BaseRespEntity<List<ProductEntity>>> newUserProduct = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            newUserProduct=getModel().getNewUserProduct();
            //将网络数据结果存储到本地sqlite数据库
            HomeDBHelper.getInstance().getDB().homeDao().insertProductAll(newUserProduct.getValue().getData());
        }else{
            //从本地sqlite数据库提取缓存的数据
            LiveData<List<ProductEntity>> liveData = homeLocalModel.getNewUserProduct();
            MutableLiveData<BaseRespEntity<List<ProductEntity>>> data= new MutableLiveData<>();
            data.setValue(new BaseRespEntity<List<ProductEntity>>(liveData.getValue()));
            return data;
        }

        return newUserProduct;
    }

    /**
     * 获取推荐核心产品数据
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct(){
        LiveData<BaseRespEntity<List<ProductEntity>>> product = null;
        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            product=getModel().getProduct();
            //将网络数据结果存储到本地sqlite数据库
            HomeDBHelper.getInstance().getDB().homeDao().insertProductAll(product.getValue().getData());
        }else{
            //从本地sqlite数据库提取缓存的数据
            LiveData<List<ProductEntity>> liveData = homeLocalModel.getProduct();
            MutableLiveData<BaseRespEntity<List<ProductEntity>>> data= new MutableLiveData<>();
            data.setValue(new BaseRespEntity<List<ProductEntity>>(liveData.getValue()));
            return data;
        }

        return product;
    }
}
