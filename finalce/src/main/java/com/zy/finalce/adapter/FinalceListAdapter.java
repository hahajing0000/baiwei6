package com.zy.finalce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zy.adapter.BaseAdapter;
import com.zy.adapter.BaseViewHolder;
import com.zy.finalce.BR;
import com.zy.finalce.R;
import com.zy.finalce.entity.FinalceEntity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author:zhangyue
 * @date:2020/6/30
 */
public class FinalceListAdapter extends BaseAdapter<FinalceEntity, BaseViewHolder> {
    public FinalceListAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding =DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_finalce,parent,false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.finalce,mDataSoure.get(position));
    }
}
