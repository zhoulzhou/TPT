package com.example.tire.datafactory;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DataService extends Service {
    private UpdateDataThread updateDataThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateDataThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        updateDataThread = new UpdateDataThread("updateDB",this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        updateDataThread.killSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
