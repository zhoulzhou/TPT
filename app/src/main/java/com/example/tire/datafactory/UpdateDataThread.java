package com.example.tire.datafactory;

import android.content.Context;

import com.example.tire.common.LogUtils;

public class UpdateDataThread extends Thread {
    private boolean isStop = false;

    public UpdateDataThread(String updateDB) {
        super(updateDB);
    }

    @Override
    public void run() {
        while (!isStop){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //update db
            LogUtils.d("UpdateDataThread run thread= " + Thread.currentThread().getName());
            TireTableOperator.getInstance().update();
        }

    }

    public void killSelf(){
        isStop = true;
    }
}
