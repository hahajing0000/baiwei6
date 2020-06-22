package com.zy.common.utils

import android.util.Log

/**
 * @author:zhangyue
 * @date:2020/6/19
 * 日志输出类
 */
object LogUtils {
    private const val TAG = "hahajing"
    private const val isDebug = true
    fun d(tag:String,log: String?) {
        if (isDebug) {
            if(tag==""){
                Log.d(TAG, log)
            }
            else{
                Log.d(TAG,"$tag : $log")
            }
        }
    }

    fun e(tag:String,log:String){
        if (isDebug) {
            if(tag==""){
                Log.e(TAG, log)
            }
            else{
                Log.e(TAG,"$tag : $log")
            }
        }
    }

    fun e(tag:String,log:Throwable){
        if (isDebug) {
            if(tag==""){
                Log.e(TAG, log.message)
            }
            else{
                val stackTrace = log.stackTrace
                for (item in stackTrace){
                    Log.e(TAG,"$tag : filename = ${item.fileName} classname = ${item.className} methodname = ${item.methodName} linenumber -> ${item.lineNumber}")
                }
                Log.e(TAG,"$tag : ${log.message}")
            }
        }
    }

    fun w(tag:String,log:String){
        if (isDebug) {
            if(tag==""){
                Log.w(TAG, log)
            }
            else{
                Log.w(TAG,"$tag : $log")
            }
        }
    }

    fun v(tag:String,log:String){
        if (isDebug) {
            if(tag==""){
                Log.v(TAG, log)
            }
            else{
                Log.v(TAG,"$tag : $log")
            }
        }
    }

    fun i(tag:String,log:String){
        if (isDebug) {
            if(tag==""){
                Log.i(TAG, log)
            }
            else{
                Log.i(TAG,"$tag : $log")
            }
        }
    }
}