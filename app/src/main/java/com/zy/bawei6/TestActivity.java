package com.zy.bawei6;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.zy.wiget.CustomProcessBar;
import com.zy.wiget.FinalceProcessBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
//    private CustomProcessBar cpbTest;
    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        cpbTest = (CustomProcessBar) findViewById(R.id.cpb_test);
//
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
//        valueAnimator.setDuration(5000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float result=(float) animation.getAnimatedValue();
//                cpbTest.setCurrentProcess(result);
//            }
//        });
//        valueAnimator.start();
        lineChart = (LineChart) findViewById(R.id.lineChart);

        initData();
    }

    private void initData() {
        List<Entry> dataSource=new ArrayList<>();

        for (int i=0;i<10;i++){
            float x=0;
            float y=0;
            dataSource.add(new Entry(makeRandom(100.0F,0.0F,2),makeRandom(100.0F,0.0F,2)));
        }

        LineDataSet dataSet = new LineDataSet(dataSource, "Label"); // add entries to dataset
//        dataSet.setColor(Color.YELLOW);
//        dataSet.setValueTextColor(...); // styling,
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh
    }

    float makeRandom(float max, float min, int scale){
        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
        BigDecimal bigDecimal = cha.setScale(scale, BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
        return bigDecimal.floatValue();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
