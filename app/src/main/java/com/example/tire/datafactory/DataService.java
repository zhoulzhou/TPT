package com.example.tire.datafactory;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.tire.common.LogUtils;

public class DataService extends Service {
    private UpdateDataThread updateDataThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("DataService onStartCommand");
        updateDataThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        updateDataThread = new UpdateDataThread("UpdateDBThread");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("DataService onDestroy");
        updateDataThread.killSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
