package com.example.tire.model;

import java.util.HashMap;

public interface ITirePressureDetectionModel {
    HashMap getTirePressureValueFromDB();

    void registerDBObserver();
    void unregisterDBObserver();

    void setOnDataChangedListener(IOnDataChangedListener listener);

}
