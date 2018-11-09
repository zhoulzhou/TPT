package com.example.tire.datafactory;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DataService extends Service {
    private UpdateDataThread updateDataThread;

    @Override
    public void onCreate() {
        super.onCreate();
        updateDataThread = new UpdateDataThread("updateDB");
        updateDataThread.start();
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
