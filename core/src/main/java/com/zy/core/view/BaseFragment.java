package com.zy.core.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.core.viewmodel.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @author:zhangyue
 * @date:2020/6/19
 * 基础的Fragment
 */
public abstract class BaseFragment<Binding extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {

    protected Binding binding;
    protected VM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        vm=createVM();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 获取布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 创建VM
     * @return
     */
    protected abstract VM createVM();
}