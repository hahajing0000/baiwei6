package com.zy.usercenter.view;

import android.text.TextUtils;
import android.view.View;

import com.zy.core.view.BaseActivity;
import com.zy.usercenter.R;
import com.zy.usercenter.databinding.ActivityLoginBinding;
import com.zy.usercenter.viewmodel.UserViewModel;

import androidx.lifecycle.LiveData;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {

    @Override
    protected void initBinding() {
        binding.setVm(vm);
        binding.setCommand(this);
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 登录按钮的点击事件
     * @param view
     */
    public void loginClick(View view){
        String username = vm.userEntity.getUsername();
        String pwd = vm.userEntity.getPwd();
        if (TextUtils.isEmpty(username)){
            showMsg(getResourceString(R.string.user_hint_input_username));
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            showMsg(getResourceString(R.string.user_hint_input_pwd));
            return;
        }
        LiveData<Boolean> result = vm.login();
        if(result.getValue()){
            showMsg(getResourceString(R.string.user_login_success));
            return;
        }
        else{
            showMsg(getResourceString(R.string.user_login_failed));
        }
    }
}
