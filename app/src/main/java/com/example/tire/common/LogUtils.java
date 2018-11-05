package com.example.tire.common;

import android.util.Log;

public class LogUtils {
    private static boolean isShow = true;
    private static final String TAG = "TirePressureDetection";

    public static void d(String msg){
        if(isShow){
            Log.d(TAG,msg);
        }
    }
}
