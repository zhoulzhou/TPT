package com.example.tire.application;

import android.app.Application;
import android.content.Context;

public class TirePressureDetectionApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        TirePressureDetectionApplication.mContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return TirePressureDetectionApplication.mContext;
    }
}
