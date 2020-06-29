package com.zy.home.model.dao;

import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Home模块的Dao
 */
@Dao
public interface HomeDao {
    /**
     * banner信息的保存和查询
     * @return
     */
    @Query("select * from tb_banner")
    List<BannerEntity> getBannerAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBannerAll(List<BannerEntity> banners);

    /**
     * 系统消息的保存和查询
     * @return
     */
    @Query("select * from tb_sysmsg")
    List<SysMsgEntity> getSysMsgAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSysMsgAll(List<SysMsgEntity> sysmsgs);

    /**
     * 产品数据的保存和查询（新用户|核心产品）
     * @return
     */
    @Query("select * from tb_product where isnew=1")
    List<ProductEntity> getNewUserProductAll();
    @Query("select * from tb_product where isnew=0")
    List<ProductEntity> getProductAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductAll(List<ProductEntity> products);
}
