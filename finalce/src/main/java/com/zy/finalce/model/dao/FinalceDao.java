package com.zy.finalce.model.dao;

import com.zy.finalce.entity.FinalceEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
@Dao
public interface FinalceDao {
    /**
     * 添加数据
     * @param entites
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<FinalceEntity> entites);

    /**
     * 根据产品类型获取对应产品数据
     * @param producttype 产品类型
     * @return
     */
    @Query("select * from tb_finalce where producttype= :producttype limit (:currentpage*:pagesize),:pagesize")
    List<FinalceEntity> findAll(int producttype,int currentpage,int pagesize);
}
