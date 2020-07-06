package com.zy.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/7/6
 */
public class CustomProcessBar extends View {
    private Paint backgroundPaint;
    private Paint currentPaint;
    private Paint txtPaint;
    /**
     * 背景矩形的颜色
     */
    private int backgoundColor;
    /**
     * 前面矩形的颜色
     */
    private int currentBackgoundColor;
    /**
     * 文本大小
     */
    private float txtSize;
    /**
     * 文本的颜色
     */
    private int txtColor;

    private int defaultWidth=300;
    private int defaultHeight=100;

    /**
     * 前面矩形的右坐标
     */
    private float currentRight=0;

    /**
     * 前面矩形的高度偏移位置
     */
    private float heightOffset=50.0F;

    /**
     * 当前进度
     */
    private Float currentProcess;

    public CustomProcessBar(Context context) {
        super(context);
    }

    public CustomProcessBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initPaint();
    }

    /**处理自定义属性
     * @param context
     * @param attrs
     */
    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProcessBar);
        if (typedArray!=null){

            backgoundColor = typedArray.getColor(R.styleable.CustomProcessBar_cpb_backgound_color, Color.LTGRAY);
            currentBackgoundColor = typedArray.getColor(R.styleable.CustomProcessBar_cpb_current_backgound_color, Color.RED);
            txtSize = typedArray.getDimension(R.styleable.CustomProcessBar_cpb_txt_size, 25);
            txtColor = typedArray.getColor(R.styleable.CustomProcessBar_cpb_txt_color,Color.rgb(204, 153, 51));//Color.argb(1, 204, 153, 51)
            currentProcess = typedArray.getFloat(R.styleable.CustomProcessBar_currentProcess,0);
            typedArray.recycle();
        }

    }

    public CustomProcessBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF=new RectF(0+getPaddingLeft(),
                0+heightOffset+getPaddingTop(),
                getMeasuredWidth()-getPaddingRight(),
                getMeasuredHeight()-getPaddingBottom());
        canvas.drawRect(rectF,backgroundPaint);

        //前面矩形的右侧坐标
        currentRight=calculateRightLocation(rectF,currentProcess);

        RectF currentRectF=new RectF(0+getPaddingLeft(),0+heightOffset+getPaddingTop(),currentRight,getMeasuredHeight()-getPaddingBottom());
        canvas.drawRect(currentRectF,currentPaint);

        canvas.drawText(currentProcess+"%",currentRight,currentRectF.top-5F,txtPaint);
    }

    /**
     * 计算前面矩形右坐标的位置
     * @param rectF 背景矩形
     * @param currentProcess 当前进度 **.*%
     * @return
     */
    private float calculateRightLocation(RectF rectF, Float currentProcess) {
        float left = rectF.left;
        float relWidth = (float) ((rectF.right - rectF.left) / 100.00 * currentProcess);
        return left+relWidth;
    }

    /**
     * 设置当前进度
     * @param currentProcess
     */
    public void setCurrentProcess(Float currentProcess) {
        this.currentProcess = currentProcess;
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            invalidate();
        }
        else{
            postInvalidate();
        }

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        backgroundPaint=new Paint();
        currentPaint=new Paint();
        txtPaint=new Paint();

        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(backgoundColor);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setDither(true);

        currentPaint.setStyle(Paint.Style.FILL);
        currentPaint.setColor(currentBackgoundColor);
        currentPaint.setAntiAlias(true);
        currentPaint.setDither(true);

        txtPaint.setTextSize(txtSize);
        txtPaint.setColor(txtColor);
        txtPaint.setTextAlign(Paint.Align.CENTER);
    }
}
