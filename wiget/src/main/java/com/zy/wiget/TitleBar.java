package com.zy.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author:zhangyue
 * @date:2020/6/23
 * 标题栏
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {
    private TitleBarClickListener titleBarClickListener;
    private TextView left;
    private TextView title;
    private TextView right;

    public TitleBar(Context context) {
        super(context);
    }

    public void setTitleBarClickListener(TitleBarClickListener listener){
        this.titleBarClickListener=listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_titlebar, null);
        left = view.findViewById(R.id.view_titlebar_left);
        title = view.findViewById(R.id.view_titlebar_title);
        right = view.findViewById(R.id.view_titlebar_right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        doCustomProps(context,attrs);

//        setBackgroundResource(R.color.colorPrimary);

        addView(view);
    }

    /**
     * 处理自定义属性
     * @param context
     * @param attrs
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void doCustomProps(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        if (typedArray!=null){
            String left_txt = typedArray.getString(R.styleable.TitleBar_left_txt);

            int left_icon = typedArray.getResourceId(R.styleable.TitleBar_left_icon, 0);
            boolean left_icon_visible = typedArray.getBoolean(R.styleable.TitleBar_left_iocn_visible, false);

            String title_txt=typedArray.getString(R.styleable.TitleBar_title_txt);

            String right_txt=typedArray.getString(R.styleable.TitleBar_right_txt);
            int right_icon=typedArray.getResourceId(R.styleable.TitleBar_right_icon,0);
            boolean right_icon_visible=typedArray.getBoolean(R.styleable.TitleBar_right_icon_visible,false);

            //处理左侧按钮
            if (!TextUtils.isEmpty(left_txt))
            {
                left.setText(left_txt);
            }
            if (left_icon!=0){
                Drawable drawable = context.getDrawable(left_icon);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                left.setCompoundDrawables(drawable,null,null,null);
            }
            if (left_icon_visible){
                left.setVisibility(View.VISIBLE);
            }
            else{
                left.setVisibility(GONE);
            }

            //处理中间标题内容
            if (!TextUtils.isEmpty(title_txt)){
                title.setText(title_txt);
            }

            if (!TextUtils.isEmpty(right_txt)){
                right.setText(right_txt);
            }

            if (right_icon!=0){
                Drawable drawable = context.getDrawable(right_icon);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                right.setCompoundDrawables(drawable,null,null,null);
            }
            if (right_icon_visible){
                right.setVisibility(VISIBLE);
            }else {
                right.setVisibility(GONE);
            }
        }
        typedArray.recycle();
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.view_titlebar_left){
            if (this.titleBarClickListener!=null){
                this.titleBarClickListener.leftClick(v);
            }
        }
        else if (v.getId()==R.id.view_titlebar_right){
            if (this.titleBarClickListener!=null){
                this.titleBarClickListener.rightClick(v);
            }
        }
    }

    public interface TitleBarClickListener{
        void leftClick(View view);
        void rightClick(View view);
    }
}
