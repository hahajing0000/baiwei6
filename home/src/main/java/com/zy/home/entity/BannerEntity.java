package com.zy.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * Banner的实体类型
 */
@Entity(tableName = "tb_banner")
public class BannerEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String imgurl;

    private String desc;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
