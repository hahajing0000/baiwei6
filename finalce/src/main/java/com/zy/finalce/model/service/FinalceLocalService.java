package com.zy.finalce.model.service;

import com.zy.core.model.IModel;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.model.database.FinalceDBHelper;

import java.util.List;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
public class FinalceLocalService implements IModel {
    /**
     * 添加数据
     * @param entites
     */
    public void addAll(List<FinalceEntity> entites){
        FinalceDBHelper.getInstance().getDB().finalceDao().addAll(entites);
    }

    /**
     * 根据产品类型获取对应产品数据
     * @param producttype 产品类型
     * @return
     */
    public List<FinalceEntity> findAll(int producttype,int currentpage,int pagesize){
        return FinalceDBHelper.getInstance().getDB().finalceDao().findAll(producttype,currentpage,pagesize);
    }
}
