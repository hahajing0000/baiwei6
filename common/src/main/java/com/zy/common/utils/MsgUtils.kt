package com.zy.common.utils

import android.content.Context
import android.widget.Toast

/**
 *@author:zhangyue
 *@date:2020/6/19
 * 消息提示工具类
 */
object MsgUtils {
    /**
     * 提示消息
     */
    fun show(cxt:Context,msg:String){
        Toast.makeText(cxt,msg,Toast.LENGTH_SHORT).show();
    }
}