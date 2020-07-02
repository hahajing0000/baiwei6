package com.zy.bawei6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zy.wiget.FinalceProcessBar;

public class TestActivity extends AppCompatActivity {
    private FinalceProcessBar fpbTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        fpbTest = (FinalceProcessBar) findViewById(R.id.fpb_test);
        int angle=360;
        fpbTest.setSweepAngle(angle);
//        fpbTest.setContent("当前进度:"+angle+"%");
    }

    @Override
    protected void onResume() {
        super.onResume();
        fpbTest.startAnimator(5000);
    }
}
