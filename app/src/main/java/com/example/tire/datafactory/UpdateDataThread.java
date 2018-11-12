package com.example.tire.datafactory;

import android.content.Context;

import com.example.tire.common.LogUtils;

public class UpdateDataThread extends Thread {
    private boolean isStop = false;
    private Context mContext;

    public UpdateDataThread(String updateDB, Context context) {
        super(updateDB);
        mContext = context;
    }

    @Override
    public void run() {
        while (!isStop){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //update db
            LogUtils.d("UpdateDataThread run");
            TireTableOperator.getInstance(mContext).insert();
        }
    }

    public void killSelf(){
        isStop = true;
    }
}
