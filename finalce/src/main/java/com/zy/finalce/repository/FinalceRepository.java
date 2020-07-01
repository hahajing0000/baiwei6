package com.zy.finalce.repository;

import com.zy.common.app.BaseAppcation;
import com.zy.common.async.CacheThreadPool;
import com.zy.common.net.NetStateUtils;
import com.zy.core.repository.Repository;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.model.api.FinalceApi;
import com.zy.finalce.model.database.FinalceDBHelper;
import com.zy.finalce.model.service.FinalceLocalService;
import com.zy.finalce.model.service.FinalceRemoteService;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @author:zhangyue
 * @date:2020/7/1
 * 理财模块的数据仓库层
 */
public class FinalceRepository extends Repository<FinalceRemoteService> {
    /**
     * 本地存储服务
     */
    private FinalceLocalService localService;
    private LiveData<BaseRespEntity<List<FinalceEntity>>> finalce;

    public FinalceRepository(LifecycleOwner _owner) {
        super(_owner);
        localService=new FinalceLocalService();
    }

    @Override
    protected FinalceRemoteService createModel() {
        return new FinalceRemoteService();
    }

    /**
     * 根据理财产品的类型获取对应数据
     * @param type 理财产品的类型
     * @param currentPage 当前页码
     * @param pageSize 每页的数据数
     * @return
     */
    public LiveData<BaseRespEntity<List<FinalceEntity>>> getFinalceByType(final int type, final int currentPage, final int pageSize){
        /**
         * 先判断当前的网络状态是否可用
         * 如果可以使用远程数据
         * 如果不可用使用本地数据
         */

        //网络可以情况加载网络数据
        if (NetStateUtils.isNetworkAvailable(BaseAppcation.getAppContext())){
            finalce = getModel().getFinalceByType(type, currentPage, pageSize);

            finalce.observe(owner, new Observer<BaseRespEntity<List<FinalceEntity>>>() {
                @Override
                public void onChanged(final BaseRespEntity<List<FinalceEntity>> listBaseRespEntity) {
                    CacheThreadPool.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            FinalceDBHelper.getInstance().getDB().finalceDao().addAll(listBaseRespEntity.getData());
                        }
                    });
                }
            });
        }else{
            final MutableLiveData<BaseRespEntity<List<FinalceEntity>>> data = new MutableLiveData<BaseRespEntity<List<FinalceEntity>>>();
            CacheThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    //从本地sqlite数据库提取缓存的数据
                    List<FinalceEntity> finalceEntities = localService.findAll(type,currentPage,pageSize);
                    data.postValue(new BaseRespEntity<List<FinalceEntity>>(finalceEntities));
                }
            });
            return data;
        }

        return finalce;
    }
}
