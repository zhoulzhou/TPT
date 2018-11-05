package com.example.tire.presenter;

public interface ITirePressureDetectionPresenter {
    float getTirePressure(int whichTire);
    float getTireTemperature(int whichTire);

    void startTireDetection();
    void stopTireDetection();
}
