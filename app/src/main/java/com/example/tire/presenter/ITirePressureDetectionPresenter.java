package com.example.tire.presenter;

import com.example.tire.view.ITirePressureDetectionView;

public interface ITirePressureDetectionPresenter {
    float getTirePressure(int whichTire);
    float getTireTemperature(int whichTire);

    void attachView(ITirePressureDetectionView view);
    void detachView();
    boolean isViewAttached();

    void startTireDetection();
    void stopTireDetection();
}
