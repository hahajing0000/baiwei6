package com.zy.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * 系统消息实体
 */
@Entity(tableName = "tb_sysmsg")
public class SysMsgEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    private int id;
    @ColumnInfo
    private String msgtype;
    @ColumnInfo
    private String msg;
    @ColumnInfo
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
