package com.example.tire.datafactory;

import com.example.tire.common.LogUtils;

public class UpdateDataThread extends Thread {
    private boolean isStop = false;

    public UpdateDataThread(String name) {
        super(name);
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
