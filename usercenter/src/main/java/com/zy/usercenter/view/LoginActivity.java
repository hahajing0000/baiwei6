package com.zy.usercenter.view;

import android.text.TextUtils;
import android.view.View;

import com.zy.common.utils.LogUtils;
import com.zy.core.view.BaseActivity;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.net.protocol.TokenRespEntity;
import com.zy.usercenter.R;
import com.zy.usercenter.databinding.ActivityLoginBinding;
import com.zy.usercenter.entity.UserEntity;
import com.zy.usercenter.model.api.UserApi;
import com.zy.usercenter.viewmodel.UserViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {
    private final String TAG=LoginActivity.class.getSimpleName();
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

        LiveData<BaseRespEntity<UserEntity>> result = vm.login();

//        if(result.getValue()){
//            showMsg(getResourceString(R.string.user_login_success));
//            return;
//        }
//        else{
//            showMsg(getResourceString(R.string.user_login_failed));
//        }

        result.observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity!=null){
                    showMsg(getResourceString(R.string.user_login_success));
                }else{
                    showMsg(getResourceString(R.string.user_login_failed));
                }
            }
        });

    }

    public void changeUrlClick(View view){
//        UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
//        userApi.getTest().enqueue(new Callback<TokenRespEntity>() {
//            @Override
//            public void onResponse(Call<TokenRespEntity> call, Response<TokenRespEntity> response) {
//                LogUtils.INSTANCE.i(TAG,"Request SUCCESS");
//            }
//
//            @Override
//            public void onFailure(Call<TokenRespEntity> call, Throwable t) {
//                LogUtils.INSTANCE.e(TAG,t);
//            }
//        });
    }
}
