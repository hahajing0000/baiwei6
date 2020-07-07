package com.zy.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.zy.common.utils.LogUtils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author:zhangyue
 * @date:2020/7/7
 */
public class CustomEditText extends AppCompatEditText {
    private final String TAG=CustomEditText.class.getSimpleName();
    /**
     * 上个链节点
     */
    private CustomEditText previous;
    /**
     * 下一个链节点
     */
    private CustomEditText next;

    /**
     * 允许最大的输入文本长度
     */
    private int maxLength=0;
    /**
     * 默认文本字号大小
     */
    private int txtSize=0;

    public CustomEditText(Context context) {
        super(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //处理自定义属性
        doCustomProp(context,attrs);
        initControllerConfig();
    }

    /**
     * 控件的一些初始化设置
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initControllerConfig() {
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setTextSize(txtSize);
        //限制输入长度 等同与 xml中设置的android:maxLength属性
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        this.addTextChangedListener(new CustomTextWatcher());
    }

    /**
     * 处理自定义属性
     * @param context
     * @param attrs
     */
    private void doCustomProp(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        if (typedArray!=null){
            maxLength=typedArray.getInteger(R.styleable.CustomEditText_cet_maxlength,4);
            txtSize= (int) typedArray.getDimension(R.styleable.CustomEditText_cet_txtsize,30);
        }
        //释放属性
        typedArray.recycle();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置下一级链节点
     * @param next
     */
    public void setNext(CustomEditText next) {
        this.next = next;
        next.previous=this;

    }

    /**
     * 设置ET的显示文本
     * @param _content
     */
    public void setCustomText(String _content){
        if (TextUtils.isEmpty(_content)){
            LogUtils.INSTANCE.w(TAG,"setCustomText method params _content is null...");
            return;
        }
        String[] strArray=_content.split(" ");
        if (strArray==null||strArray.length==0){
            LogUtils.INSTANCE.w(TAG,"strArray is null or length = 0");
            return;
        }
        String tempTxt="";
        for (int i=0;i<strArray.length;i++){
            if (TextUtils.isEmpty(strArray[i].trim())){
                continue;
            }
            tempTxt=strArray[i];
            setText(tempTxt);
            break;
        }

        //去除上面已使用文本后的字符串
        String substring = _content.substring(_content.indexOf(tempTxt) + tempTxt.length());
        if (next!=null){
            next.setCustomText(substring);
        }

        /**
         * 设置光标的显示位置
         */
        setSelection(getText().length());
    }

    private class CustomTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String content = getText().toString();
            int length = content.length();
            if (length>=maxLength){
                setTextColor(Color.LTGRAY);
                if (next!=null){
                    //清除当前ET的光标
                    clearFocus();
                    //下一个节点控件获取光标
                    next.requestFocus();
                }
            }else if (length==0){
                if (previous!=null){
                    //清除当前ET的光标
                    clearFocus();
                    //上一个节点获取光标
                    previous.requestFocus();
                }
            }
        }
    }
}
