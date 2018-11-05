package com.example.tire.model;

public class TirePressureDetectionModel implements ITirePressureDetectionModel {
    private volatile static TirePressureDetectionModel instance = null;

    public TirePressureDetectionModel(){

    }

    public static TirePressureDetectionModel getInstance(){
        if(instance == null){
            synchronized(TirePressureDetectionModel.class){
                if(instance == null){
                    instance = new TirePressureDetectionModel();
                }
            }
        }
        return instance;
    }

    @Override
    public float getTirePressureFromDB(int whichTire) {
        return 10;
    }

    @Override
    public float getTireTemperatureFromDB(int whichTire) {
        return 11;
    }
}
