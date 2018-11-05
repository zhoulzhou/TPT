package com.example.tire.view;

public interface ITirePressureDetectionView {
    void showTirePressureFL(float pressure, float temperature);
    void showTirePressureFR(float pressure, float temperature);
    void showTirePressureBL(float pressure, float temperature);
    void showTirePressureBR(float pressure, float temperature);
}
