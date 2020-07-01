package com.zy.home.model.database;

import com.zy.common.app.BaseAppcation;
import com.zy.storage.room.BaseDBHelper;

import androidx.room.Room;

/**
 * @author:zhangyue
 * @date:2020/6/29
 */
public class HomeDBHelper{
    /**
     * 数据库的名称
     */
    private final static String DBNAME="home_db";
    private static HomeDBHelper instance=new HomeDBHelper();
    private final HomeDataBase db;

    private HomeDBHelper(){
        db = Room.databaseBuilder(BaseAppcation.getAppContext(), HomeDataBase.class, DBNAME).build();
    }
    public static HomeDBHelper getInstance(){
        return instance;
    }

    /**
     * 获取到DB对象
     * @return
     */
    public HomeDataBase getDB(){

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
