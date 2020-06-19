package com.zy.core.view;

import android.os.Bundle;

import com.zy.common.utils.MsgUtils;
import com.zy.core.viewmodel.BaseViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author:zhangyue
 * @date:2020/6/19
 * 基础的Activity
 */
public abstract class BaseActivity<Binding extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {

    protected Binding binding;
    protected VM vm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        vm=createVM();
        initBinding();
    }

    /**
     * 初始化绑定
     */
    protected abstract void initBinding();

    /**
     * 创建VM
     * @return
     */
    protected abstract VM createVM();

    /**
     * 设置布局id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 显示提示信息
     * @param msg
     */
    protected void showMsg(String msg){
        MsgUtils.INSTANCE.show(this,msg);
    }

    /**
     * 换取系统资源中的String  支持全球化
     * @param strId String 资源的ID
     * @return
     */
    protected String getResourceString(int strId){
        return getResources().getString(strId);
    }
}
