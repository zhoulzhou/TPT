package com.example.tire.datafactory;

public class UpdateDataThread extends Thread {
    private boolean isStop = true;

    public UpdateDataThread(String updateDB) {
        super(updateDB);
    }

    @Override
    public void run() {
        while (!isStop){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //update db

        }
    }

    public void killSelf(){
        isStop = true;
    }
}
