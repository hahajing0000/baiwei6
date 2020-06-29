package com.zy.home.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author:zhangyue
 * @date:2020/6/29
 * 金融产品数据实体
 */
@Entity(tableName = "tb_product")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    private int id;
    @ColumnInfo
    private String productname;
    @ColumnInfo
    private String productdesc;
    @ColumnInfo
    private int producttype;
    @ColumnInfo
    private double yearrate;
    @ColumnInfo
    private double totalamount;
    @ColumnInfo
    private int saleamount;
    @ColumnInfo
    private String labels;
    @ColumnInfo
    private int lockdays;
    @ColumnInfo
    private int minbugamount;
    @ColumnInfo
    private int isnew;
    @ColumnInfo
    private int startlevel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public int getProducttype() {
        return producttype;
    }

    public void setProducttype(int producttype) {
        this.producttype = producttype;
    }

    public double getYearrate() {
        return yearrate;
    }

    public void setYearrate(double yearrate) {
        this.yearrate = yearrate;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public int getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(int saleamount) {
        this.saleamount = saleamount;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getLockdays() {
        return lockdays;
    }

    public void setLockdays(int lockdays) {
        this.lockdays = lockdays;
    }

    public int getMinbugamount() {
        return minbugamount;
    }

    public void setMinbugamount(int minbugamount) {
        this.minbugamount = minbugamount;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getStartlevel() {
        return startlevel;
    }

    public void setStartlevel(int startlevel) {
        this.startlevel = startlevel;
    }
}
