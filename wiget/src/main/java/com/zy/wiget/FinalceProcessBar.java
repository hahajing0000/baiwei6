package com.zy.wiget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/7/1
 */
public class FinalceProcessBar extends View {

    private FinalceParoessBarClickListener clickListener;

    /**
     * 边框画笔
     */
    private Paint storkPaint;
    /**
     * 文本的画笔
     */
    private Paint txtPaint;

    /**
     * 背景画笔
     */
    private Paint backgoundPaint;

    /**
     * 默认线条颜色和字体颜色
     */
    private int defaultColor=Color.YELLOW;
    /**
     * 字体大小
     */
    private float defaultTextSize=20.0f;
    /**
     * 线条宽度
     */
    private float defaultStorkWidth=5.0f;
    /**
     * 文本颜色
     */
    private int txtColor;
    /**
     * 文本大小
     */
    private float txtSize;
    /**
     * 边框颜色
     */
    private int strokColor;
    /**
     * 边框的大小
     */
    private float storkWidth;

    /**
     * 默认控件的宽与高
     */
    private int default_width=100;
    private int default_height=100;

    /**
     * 默认的相对角度
     */
    private float sweepAngle=360;

    /**
     * 文本显示内容
     */
    private String txtContent="";

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    public FinalceProcessBar(Context context) {
        super(context);
    }

    public FinalceProcessBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //自定义属性
        doCustomProp(context,attrs);
        //初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        storkPaint=new Paint();
        txtPaint=new Paint();
        backgoundPaint=new Paint();

        storkPaint.setColor(strokColor==0?defaultColor:strokColor);
        storkPaint.setStyle(Paint.Style.STROKE);
        storkPaint.setStrokeWidth(storkWidth==0?defaultStorkWidth:storkWidth);
        storkPaint.setAntiAlias(true);
        storkPaint.setDither(true);

        backgoundPaint.setColor(Color.LTGRAY);
        backgoundPaint.setStyle(Paint.Style.STROKE);
        backgoundPaint.setStrokeWidth(storkWidth==0?defaultStorkWidth:storkWidth);
        backgoundPaint.setAntiAlias(true);
        backgoundPaint.setDither(true);



        txtPaint.setColor(txtColor==0?defaultColor:txtColor);
        txtPaint.setTextSize(txtSize==0?defaultTextSize:txtSize);
        txtPaint.setAntiAlias(true);
        txtPaint.setDither(true);
        txtPaint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 处理自定义属性
     * @param context
     * @param attrs
     */
    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FinalceProcessBar);
        if (typedArray!=null){
            txtColor = typedArray.getColor(R.styleable.FinalceProcessBar_textColor, defaultColor);
            txtSize = typedArray.getDimension(R.styleable.FinalceProcessBar_textSize, defaultTextSize);
            strokColor = typedArray.getColor(R.styleable.FinalceProcessBar_stork_color, defaultColor);
            storkWidth = typedArray.getDimension(R.styleable.FinalceProcessBar_stork_width, defaultStorkWidth);

            sweepAngle = typedArray.getFloat(R.styleable.FinalceProcessBar_sweepangle, sweepAngle);
            txtContent = typedArray.getString(R.styleable.FinalceProcessBar_txtcontent);
        }
        typedArray.recycle();
    }

    public FinalceProcessBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * widthMeasureSpec|heightMeasureSpec  是一个32位的整型  前两位代表Mode  后30位是具体的大小值
     * Mode有3中模式：
     * UNSPECIFIED - 不受限制  父布局对子布局没有任何的约束
     * EXACTLY - match_parent  XXdp
     * AT_MOST - warp_content
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,default_height);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,default_height);
        }

    }

    /**
     * 设置相对角度
     * @param sweepAngle
     */
    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

//    /**
//     * 设置文本内容
//     * @param txtContent
//     */
//    public void setTxtContent(String txtContent) {
//        this.txtContent = txtContent;
//    }

    /**
     * 开始运行动画效果
     * @param duration
     */
    public void startAnimator(long duration){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, this.sweepAngle);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float)animation.getAnimatedValue();
                setSweepAngle(value);
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }
                else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    /**
     * 文本变化的动画
     * @param duration
     * @param endValue
     */
    public void startContentAnimator(long duration,float endValue){
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,endValue);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float result= (float) animation.getAnimatedValue();
                if (result%1==0){
                    setTxtContent(result+"%");
                }

                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }
                else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 确定圆弧的绘制范围 最小外接矩形 并考虑padding因素
         */
        float left=0.0f+getPaddingLeft();
        float top=0.0f+getPaddingTop();
        float right=this.getMeasuredWidth()-getPaddingRight();
        float bottom=this.getMeasuredHeight()-getPaddingBottom();
        RectF rectF=new RectF(left,top,right,bottom);



        //绘制背景圆弧
        canvas.drawArc(rectF,0,360,false,backgoundPaint);
        //绘制圆弧
        canvas.drawArc(rectF,0,sweepAngle,false,storkPaint);

        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float distance=(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
        float baseline =rectF.centerY()+distance;

        if (null!=txtContent){
            //绘制文本
            canvas.drawText(txtContent,rectF.centerX(),baseline,txtPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (null!=clickListener){
                    clickListener.onClick(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置接口回调 处理点击事件的监听方法
     * @param clickListener
     */
    public void setClickListener(FinalceParoessBarClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface FinalceParoessBarClickListener{
        void onClick(View view);
    }
}
