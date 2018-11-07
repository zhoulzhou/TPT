package com.example.tire.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tire.R;
import com.example.tire.common.LogUtils;
import com.example.tire.model.TirePressureDetectionModel;
import com.example.tire.presenter.ITirePressureDetectionPresenter;
import com.example.tire.presenter.TirePressureDetectionPresenter;

public class TirePressureDetectionFragment extends Fragment implements ITirePressureDetectionView {
    private TwoTextView mTirePressureFLText, mTirePressureFRText;
    private TwoTextView mTirePressureBLText, mTirePressureBRText;
    private ImageView mTirePressureFL, mTirePressureFR;
    private ImageView mTirePressureBL, mTirePressureBR;
    private ITirePressureDetectionPresenter mTirePressureDetectionPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tire_pressure_detection_layout, container, false);
        mTirePressureFLText = (TwoTextView) view.findViewById(R.id.tire_fl_text);
        mTirePressureFRText = (TwoTextView) view.findViewById(R.id.tire_fr_text);
        mTirePressureBLText = (TwoTextView) view.findViewById(R.id.tire_bl_text);
        mTirePressureBRText = (TwoTextView) view.findViewById(R.id.tire_br_text);
        mTirePressureFL = (ImageView) view.findViewById(R.id.tire_fl);
        mTirePressureFR = (ImageView) view.findViewById(R.id.tire_fr);
        mTirePressureBL = (ImageView) view.findViewById(R.id.tire_bl);
        mTirePressureBR = (ImageView) view.findViewById(R.id.tire_br);

        mTirePressureDetectionPresenter = new TirePressureDetectionPresenter(TirePressureDetectionModel.getInstance());
        mTirePressureDetectionPresenter.attachView(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d("onDestroyView");
        mTirePressureDetectionPresenter.detachView();
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("onStart");
        mTirePressureDetectionPresenter.startTireDetection();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("onResume");
        mTirePressureDetectionPresenter.startTireDetection();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("onPause");
        mTirePressureDetectionPresenter.stopTireDetection();
    }

    @Override
    public void showTirePressureFL(float pressure, float temperature) {
        mTirePressureFLText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureFL.setImageResource(R.color.red);
        }else{
            mTirePressureFL.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureFR(float pressure, float temperature) {
        LogUtils.d("showTirePressureFR pressure= " + pressure);
        mTirePressureFRText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureFR.setImageResource(R.color.red);
        }else{
            mTirePressureFR.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureBL(float pressure, float temperature) {
        mTirePressureBLText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureBL.setImageResource(R.color.red);
        }else{
            mTirePressureBL.setImageResource(R.color.blue);
        }
    }

    @Override
    public void showTirePressureBR(float pressure, float temperature) {
        mTirePressureBRText.setValue(pressure,temperature);
        if(isDangerous(pressure,temperature)){
            mTirePressureBR.setImageResource(R.color.red);
        }else{
            mTirePressureBR.setImageResource(R.color.blue);
        }
    }

    private boolean isDangerous(float pressure, float temperature){
        return pressure < 2 || pressure > 8 || temperature < 20 || temperature > 80;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTirePressureDetectionPresenter.stopTireDetection();
    }
}
