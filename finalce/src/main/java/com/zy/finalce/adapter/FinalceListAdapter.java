package com.zy.finalce.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;

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

    private RatingBar bar;

    public FinalceListAdapter(Context _context) {
        super(_context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding =DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_finalce,parent,false);
        bar = binding.getRoot().findViewById(R.id.rb_bar);
        doBarHeight(bar);
        return new BaseViewHolder(binding);
    }

    private void doBarHeight(RatingBar bar) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.start_checked);
        int height = bitmap.getHeight();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height);
        bar.setLayoutParams(layoutParams);
    }

    @Override
    protected void bindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.finalce,mDataSoure.get(position));
    }
}
