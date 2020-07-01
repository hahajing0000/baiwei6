package com.zy.finalce.viewmodel;

import com.zy.core.viewmodel.BaseViewModel;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.repository.FinalceRepository;
import com.zy.net.protocol.BaseRespEntity;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

/**
 * @author:zhangyue
 * @date:2020/6/30
 * 理财模块的VM层
 */
public class FinalceViewModel extends BaseViewModel {
    public FinalceViewModel(LifecycleOwner _owner) {
        super(_owner);
        registerRepository(FinalceRepository.class.getSimpleName(),new FinalceRepository(_owner));
    }

    /**
     * 根据理财产品的类型获取对应数据
     * @param type 理财产品的类型
     * @param currentPage 当前页码
     * @param pageSize 每页的数据数
     * @return
     */
    public LiveData<BaseRespEntity<List<FinalceEntity>>> getFinalceByType(int type, int currentPage, int pageSize){
        FinalceRepository repository = getRepository(FinalceRepository.class.getSimpleName());
        return repository.getFinalceByType(type,currentPage,pageSize);
    }

}
