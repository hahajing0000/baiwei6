package com.zy.bawei6;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.zy.wiget.CustomEditText;
import com.zy.wiget.CustomProcessBar;
import com.zy.wiget.FinalceProcessBar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
//    private CustomProcessBar cpbTest;
//    private BarChart lineChart;
    private CustomEditText cet1;
    private CustomEditText cet2;
    private CustomEditText cet3;
    private CustomEditText cet4;


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

//        lineChart = (BarChart) findViewById(R.id.lineChart);
//
//        initData();

        initView();
        initData();
    }

    private void initData() {
        cet1.setNext(cet2);
        cet2.setNext(cet3);
        cet3.setNext(cet4);
        cet1.setCustomText("0011 9988 7766 543");
    }

    private void initView() {

        cet1 = (CustomEditText) findViewById(R.id.cet_1);
        cet2 = (CustomEditText) findViewById(R.id.cet_2);
        cet3 = (CustomEditText) findViewById(R.id.cet_3);
        cet4 = (CustomEditText) findViewById(R.id.cet_4);
    }

//    private void initData() {
//        List<BarEntry> dataSource=new ArrayList<>();
//
//        for (int i=0;i<10;i++){
//            float x=0;
//            float y=0;
//            dataSource.add(new BarEntry(makeRandom(100.0F,0.0F,2),makeRandom(100.0F,0.0F,2)));
//        }
//
//        BarDataSet dataSet = new BarDataSet(dataSource, "XX公司第三季度财务统计数据报表"); // add entries to dataset
//        dataSet.setColor(Color.RED);
//        List<Integer> colors=new ArrayList<>();
//        colors.add(Color.BLUE);
//        colors.add(Color.RED);
//        colors.add(Color.GRAY);
//        dataSet.setValueTextColors(colors);
////        dataSet.setColor(Color.YELLOW);
////        dataSet.setValueTextColor(...); // styling,
//        BarData lineData = new BarData(dataSet);
//        lineChart.setData(lineData);
//        lineChart.invalidate(); // refresh
//    }
//
//    float makeRandom(float max, float min, int scale){
//        BigDecimal cha = new BigDecimal(Math.random() * (max-min) + min);
//        BigDecimal bigDecimal = cha.setScale(scale, BigDecimal.ROUND_HALF_UP);//保留 scale 位小数，并四舍五入
//        return bigDecimal.floatValue();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

}
