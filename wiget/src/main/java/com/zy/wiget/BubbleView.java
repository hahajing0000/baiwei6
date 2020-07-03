package com.zy.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/7/3
 * 气泡控件
 */
public class BubbleView extends View {
    private Paint paint;
    private Paint storkPaint;
    private Paint txtPaint;
    //背景色
    private int backgroundColor;
    //边框宽度
    private float storkWidth;
    //边框颜色
    private int storkColor;
    //文本颜色
    private int contentColor;
    //文本的字体大小
    private float contentSize;

    //控件默认大小
    private int defaultWidth,defaultHeight=20;
    private String content;

    //半径
    float radius=20;

    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initPaint();
    }


    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 处理自定义属性
     * @param context
     * @param attrs
     */
    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
        if (typedArray!=null){
            backgroundColor = typedArray.getColor(R.styleable.BubbleView_backgroundColor, Color.RED);
            storkWidth = typedArray.getDimension(R.styleable.BubbleView_storkWidth, 0);
            storkColor = typedArray.getColor(R.styleable.BubbleView_storkColor, Color.WHITE);
            contentColor = typedArray.getColor(R.styleable.BubbleView_contentColor, Color.LTGRAY);
            contentSize = typedArray.getDimension(R.styleable.BubbleView_contentSize, 20);

            content = typedArray.getString(R.styleable.BubbleView_bubbleContent);

            radius=typedArray.getFloat(R.styleable.BubbleView_bubbleRadius,20.0F);

        }
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint=new Paint();
        txtPaint=new Paint();
        storkPaint=new Paint();

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(storkWidth);
        paint.setColor(backgroundColor);
        paint.setAntiAlias(true);
        paint.setDither(true);

        storkPaint.setStyle(Paint.Style.STROKE);
        storkPaint.setStrokeWidth(storkWidth);
        storkPaint.setColor(storkColor);
        storkPaint.setAntiAlias(true);
        storkPaint.setDither(true);

        txtPaint.setColor(contentColor);
        txtPaint.setTextSize(contentSize);
        txtPaint.setTextAlign(Paint.Align.CENTER);
        txtPaint.setAntiAlias(true);
        txtPaint.setDither(true);
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

        float centerX=getMeasuredWidth()/2;
        float centerY=getMeasuredHeight()/2;
        canvas.drawCircle(centerX,centerY,radius,paint);

        canvas.drawCircle(centerX,centerY,radius+storkWidth,storkPaint);

        RectF rectF=new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float distance=(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
        float baseline =rectF.centerY()+distance;

        if (null!=content){
            //绘制文本
            canvas.drawText(content,rectF.centerX(),baseline,txtPaint);
        }
    }
}
