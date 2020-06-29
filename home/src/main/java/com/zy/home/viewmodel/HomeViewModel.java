package com.zy.home.viewmodel;

import com.zy.common.app.BaseAppcation;
import com.zy.common.net.NetStateUtils;
import com.zy.core.repository.Repository;
import com.zy.core.viewmodel.BaseViewModel;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.database.HomeDBHelper;
import com.zy.home.repository.HomeRepository;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/28
 */
public class HomeViewModel extends BaseViewModel {
    public HomeViewModel(){
        registerRepository(HomeRepository.class.getSimpleName(),new HomeRepository());
    }

    /**
     * 获取Banner实体信息
     * @return
     */
    public LiveData<BaseRespEntity<List<BannerEntity>>> getBanner(){
       HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
       return repository.getBanner();
    }

    /**
     * 获取系统消息
     * @return
     */
    public LiveData<BaseRespEntity<List<SysMsgEntity>>> getSystemMsgs(){
        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getSystemMsgs();
    }

    /**
     * 获取新用户的推荐产品
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getNewUserProduct(){

        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getNewUserProduct();
    }

    /**
     * 获取推荐核心产品数据
     * @return
     */
    public LiveData<BaseRespEntity<List<ProductEntity>>> getProduct(){
        HomeRepository repository= super.getRepository(HomeRepository.class.getSimpleName());
        return repository.getProduct();
    }
}
