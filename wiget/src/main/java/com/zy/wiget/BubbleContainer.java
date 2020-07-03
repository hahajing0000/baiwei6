package com.zy.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author:zhangyue
 * @date:2020/7/3
 */
public class BubbleContainer extends RelativeLayout {
    private BubbleView bvBubblecontainer;

    public BubbleContainer(Context context) {
        super(context);
    }

    public BubbleContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.viewgroup_bubble, null, false);
        bvBubblecontainer = (BubbleView) view.findViewById(R.id.bv_bubblecontainer);
        addView(view);
    }

    public BubbleContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
