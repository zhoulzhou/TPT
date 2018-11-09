package com.example.tire.datafactory;

public class UpdateDataThread extends Thread {
    private boolean isStop = false;

    public UpdateDataThread(String updateDB) {
        super(updateDB);
    }

    @Override
    public void run() {
        while (!isStop){

        }
    }

    public void killSelf(){
        isStop = true;
    }
}
