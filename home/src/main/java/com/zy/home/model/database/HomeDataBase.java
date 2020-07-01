package com.zy.home.model.database;

import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.ProductEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.model.dao.HomeDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author:zhangyue
 * @date:2020/6/29
 */
@Database(entities = {BannerEntity.class, SysMsgEntity.class, ProductEntity.class},version = 1,exportSchema = false)
public abstract class HomeDataBase extends RoomDatabase {
    public abstract HomeDao homeDao();
}