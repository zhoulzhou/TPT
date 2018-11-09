package com.example.tire.datafactory;

import android.content.Context;

public class UpdateDataThread extends Thread {
    private boolean isStop = true;
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
            TireTableOperator.getInstance(mContext).insert();
        }
    }

    public void killSelf(){
        isStop = true;
    }
}
