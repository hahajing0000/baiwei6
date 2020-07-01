package com.zy.finalce.model.database;

import com.zy.common.app.BaseAppcation;

import androidx.room.Room;

/**
 * @author:zhangyue
 * @date:2020/7/1
 * 理财模块的数据库辅助类
 */
public class FinalceDBHelper {
    /**
     * 数据库的名称
     */
    private final static String DBNAME="finalce_db";
    private static FinalceDBHelper instance=new FinalceDBHelper();
    private final FinalceDataBase db;

    private FinalceDBHelper(){
        db = Room.databaseBuilder(BaseAppcation.getAppContext(), FinalceDataBase.class, DBNAME).build();
    }
    public static FinalceDBHelper getInstance(){
        return instance;
    }

    /**
     * 获取到DB对象
     * @return
     */
    public FinalceDataBase getDB(){

        return db;
    }

    /**
     * 关闭数据库
     */
    public void closeDB(){
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
