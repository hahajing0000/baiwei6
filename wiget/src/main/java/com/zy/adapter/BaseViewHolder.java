package com.zy.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:zhangyue
 * @date:2020/6/30
 * ViewHolder的基类
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding mBinding;
    public BaseViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.mBinding=binding;
    }

    /**
     * 获取binding对象
     * @return
     */
    public ViewDataBinding getBinding(){
        return mBinding;
    }
}
