package com.zy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:zhangyue
 * @date:2020/6/30
 * 自己封装的符合MVVM架构的 adapter
 */
public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    /**
     * 上下文环境
     */
    public Context mContext;
    /**
     * 数据源
     */
    public List<T> mDataSoure;
    public BaseAdapter(Context _context){
        this.mContext=_context;
        this.mDataSoure=new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createVH(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindVH(holder,position);
    }

    @Override
    public int getItemCount() {
        return this.mDataSoure.size();
    }

    /**
     * 加载|初始化数据
     * @param _dataSource
     */
    public void loadDataSource(List<T> _dataSource){
        if (this.mDataSoure!=null&&_dataSource!=null){
            this.mDataSoure.clear();
            this.mDataSoure.addAll(_dataSource);
            notifyDataSetChanged();
        }
    }

    /**
     * 追加数据
     * @param _dataSource
     */
    public void appendDataSource(List<T> _dataSource){
        if (this.mDataSoure!=null&&_dataSource!=null){
            this.mDataSoure.addAll(_dataSource);
            notifyDataSetChanged();
        }
    }

    /**
     * 创建ViewHolder 交由子类实现
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract VH createVH(ViewGroup parent, int viewType);

    /**
     * 绑定ViewHolder  交由子类来说实现
     * @param holder
     * @param position
     */
    protected abstract void bindVH(VH holder, int position);
}
