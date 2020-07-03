package com.zy.finalce.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zy.common.utils.LogUtils;
import com.zy.finalce.R;
import com.zy.router.RouterPath;

@Route(path = RouterPath.Finalce_BUY)
public class BuyActivity extends AppCompatActivity {
    private final String TAG=BuyActivity.class.getSimpleName();
    @Autowired(name = "procductid")
    public int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ARouter.getInstance().inject(this);

        LogUtils.INSTANCE.w(TAG,"params->"+data);
    }
}
