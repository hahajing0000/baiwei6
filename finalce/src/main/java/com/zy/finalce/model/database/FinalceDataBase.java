package com.zy.finalce.model.database;

import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.model.dao.FinalceDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
@Database(entities = {FinalceEntity.class},version = 1,exportSchema = false)
public abstract class FinalceDataBase extends RoomDatabase {
    public abstract FinalceDao finalceDao();
}
