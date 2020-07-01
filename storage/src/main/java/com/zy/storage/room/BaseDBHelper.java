package com.zy.storage.room;

import com.zy.common.app.BaseAppcation;

import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
public class BaseDBHelper<DB extends RoomDatabase> {
    /**
     * 数据库的名称
     */
    private String DBNAME="";
    private static BaseDBHelper instance=new BaseDBHelper();
    private final DB db;
    public BaseDBHelper(){
        db = (DB) Room.databaseBuilder(BaseAppcation.getAppContext(),RoomDatabase.class, DBNAME).build();
    }


    public static BaseDBHelper getInstance(){
        return instance;
    }

    /**
     * 设置数据库名称
     * @param dbname
     */
    protected void setDbname(String dbname){
        DBNAME=dbname;
    }

    /**
     * 获取到DB对象
     * @return
     */
    public DB getDB(){

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

