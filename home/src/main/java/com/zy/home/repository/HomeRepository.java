package com.zy.home.repository;

import com.zy.common.app.BaseAppcation;
import com.zy.common.async.CacheThreadPool;
import com.zy.common.net.NetStateUtils;
import com.zy.core.repository.Repository;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.database.HomeDBHelper;
import com.zy.home.model.service.HomeLocalModel;
import com.zy.home.model.service.HomeRemoteModel;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Home模块的数据仓库
 */
public class HomeRepository extends Repository<HomeRemoteModel> {
    private final String TAG=HomeRepository.class.getSimpleName();
    /**
     * Home模块本地Model
     */
    private HomeLocalModel homeLocalModel=null;

    public HomeRepository(LifecycleOwner _owner){
        super(_owner);
        homeLocalModel = new HomeLocalModel();
    }

    @Override
    protected HomeRemoteModel createModel() {
        return new HomeRemoteModel();
    }

    LiveData<BaseRespEntity<List<BannerEntity>>> banner = null;
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

        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            banner=getModel().getBanner();

            banner.observe(owner, new Observer<BaseRespEntity<List<BannerEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<BannerEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDB().homeDao().insertBannerAll(listBaseRespEntity.getData());
                        }
                    });

                }
            });
        }else{
            final MutableLiveData<BaseRespEntity<List<BannerEntity>>> data = new MutableLiveData<BaseRespEntity<List<BannerEntity>>>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    //从本地sqlite数据库提取缓存的数据
                    List<BannerEntity> banner1 = homeLocalModel.getBanner();
                    data.postValue(new BaseRespEntity<List<BannerEntity>>(banner1));
                }
            });
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
            final MutableLiveData<BaseRespEntity<List<SysMsgEntity>>> data= new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<SysMsgEntity> liveData = homeLocalModel.getSystemMsgs();
                    data.postValue(new BaseRespEntity<List<SysMsgEntity>>(liveData));
                }
            });

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
            final MutableLiveData<BaseRespEntity<List<ProductEntity>>> data= new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> liveData = homeLocalModel.getNewUserProduct();

                    data.postValue(new BaseRespEntity<List<ProductEntity>>(liveData));
                }
            });

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
            final MutableLiveData<BaseRespEntity<List<ProductEntity>>> data= new MutableLiveData<>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> liveData = homeLocalModel.getProduct();

                    data.postValue(new BaseRespEntity<List<ProductEntity>>(liveData));
                }
            });
            return data;
        }

        return product;
    }
}
